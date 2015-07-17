package com.digiwes.product.spec;

import com.digiwes.basetype.TimePeriod;
import com.digiwes.common.enums.CommonErrorCode;
import com.digiwes.common.enums.ProdSpecEnum;
import com.digiwes.common.enums.ProdSpecErrorCode;
import com.digiwes.utils.DateUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by zhaoyp on 2015/7/16.
 */
public class ProductSpecCharacteristicTest {

    private ProductSpecCharacteristic prodSpecChar;
    private ProductSpecCharacteristic exceptProdSpecChar;
    private ProductSpecCharacteristic configSpecChar;

    private static TimePeriod validFor;
    @BeforeClass
    public static void initVliadFor()throws ParseException{
        validFor = new TimePeriod(DateUtils.datetimeFormat.parse("2015-06-01  00:00:00"),DateUtils.datetimeFormat.parse("2015-08-01  23:59:59"));
    }
    @Before
    public void createProductSpecCharacteristic()throws ParseException {
        prodSpecChar = new ProductSpecCharacteristic("1", "height", ProdSpecEnum.ProdSpecType.NUMERIC.getName(), validFor, "false",  1,  1, true, "height","");
        configSpecChar=new ConfigurableProductSpecCharacteristic("2", "Memory", ProdSpecEnum.ProdSpecType.NUMERIC.getName(),validFor, "unique",1,1, true, "height","");
        ProductSpecCharacteristicValue value=new ProductSpecCharacteristicValue(ProdSpecEnum.ProdSpecType.NUMERIC.getName(),false,"GHz",validFor,"2.7");
        configSpecChar.assignValue(value);
        value=new ProductSpecCharacteristicValue(ProdSpecEnum.ProdSpecType.NUMERIC.getName(),true,"GHz",new TimePeriod(DateUtils.datetimeFormat.parse("2015-05-03 12:00:00"),DateUtils.datetimeFormat.parse("2015-07-21 23:59:59")),"2.9");
        configSpecChar.assignValue(value);
    }
    @Test
    public void testAssignValue() throws Exception {
        Set<ProductSpecCharacteristicValue> exceptProductSpecCharacteristicValues=new HashSet<ProductSpecCharacteristicValue>();
        int result = CommonErrorCode.SUCCESS.getCode();
        ProductSpecCharacteristicValue prodSpecCharValue=null;
        result=prodSpecChar.assignValue(prodSpecCharValue);
        assertEquals("productSpecCharValue is null", ProdSpecErrorCode.PROD_SPEC_CHAR_VALUE_IS_NULL.getCode(),result);
        assertEquals("productSpecCharValue is null",exceptProductSpecCharacteristicValues,prodSpecChar.getProdSpecCharValue());

        prodSpecCharValue = new ProductSpecCharacteristicValue(ProdSpecEnum.ProdSpecType.TEXT.getName(),false, "GHz", validFor, "8", "", "");
        result=prodSpecChar.assignValue(prodSpecCharValue);
        assertEquals("The value type of Character and CharacterValue value is different", ProdSpecErrorCode.PROD_SPEC_CHAR_TYPE_DIFFERENT_CHAR_VALUE_TYPE.getCode(),result);
        assertEquals("The value type of Character and CharacterValue value is different",exceptProductSpecCharacteristicValues,prodSpecChar.getProdSpecCharValue());

        prodSpecCharValue = new ProductSpecCharacteristicValue(ProdSpecEnum.ProdSpecType.NUMERIC.getName(), true,"GHz", validFor, "8");
        exceptProductSpecCharacteristicValues.add(prodSpecCharValue) ;
        result=prodSpecChar.assignValue(prodSpecCharValue);
        assertEquals("add a normal value", CommonErrorCode.SUCCESS.getCode(), result);
        assertEquals("add a normal value", 1, prodSpecChar.getProdSpecCharValue().size());
        assertEquals("add a normal value",exceptProductSpecCharacteristicValues,prodSpecChar.getProdSpecCharValue());


        result= prodSpecChar.assignValue(prodSpecCharValue);
        assertEquals("Add a duplicate value",CommonErrorCode.SUCCESS.getCode(),result);
        assertEquals("Add a duplicate value", 1, prodSpecChar.getProdSpecCharValue().size());
        assertEquals("Add a duplicate value",exceptProductSpecCharacteristicValues,prodSpecChar.getProdSpecCharValue());

        prodSpecCharValue = new ProductSpecCharacteristicValue(ProdSpecEnum.ProdSpecType.NUMERIC.getName(),true, "GHz", validFor, "8");
        result= prodSpecChar.assignValue(prodSpecCharValue);
        assertEquals("Add a duplicate value ,Values are the same as before. ",CommonErrorCode.SUCCESS.getCode(),result);
        assertEquals("Add a duplicate value ,Values are the same as before. ", 1, prodSpecChar.getProdSpecCharValue().size());
        assertEquals("Add a duplicate value ,Values are the same as before.",exceptProductSpecCharacteristicValues,prodSpecChar.getProdSpecCharValue());
    }

    @Test
    public void testRetrieveValue() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        Date time=null;
        List<ProductSpecCharacteristicValue> prodSpecCharValues=null;
        List<ProductSpecCharacteristicValue> exceptProductSpecCharacteristicValues=new ArrayList<ProductSpecCharacteristicValue>();
        try {
            prodSpecCharValues=prodSpecChar.retrieveValue(time);
            fail("query the value of Charistist:time is null, expected IllegalArgumentException for time");
        } catch (IllegalArgumentException ex) {
        }

        time=new Date();
        prodSpecCharValues=prodSpecChar.retrieveValue(time);
        assertEquals("query the value of CharististValue:No value of the Characteristic", 0, prodSpecCharValues.size());

        ProductSpecCharacteristicValue value=new ProductSpecCharacteristicValue(ProdSpecEnum.ProdSpecType.NUMERIC.getName(),false,"GHz",validFor,"2.7");
        exceptProductSpecCharacteristicValues.add(value);
        value=new ProductSpecCharacteristicValue(ProdSpecEnum.ProdSpecType.NUMERIC.getName(),true,"GHz",new TimePeriod(DateUtils.datetimeFormat.parse("2015-05-03 12:00:00"),DateUtils.datetimeFormat.parse("2015-07-21 23:59:59")),"2.9");
        exceptProductSpecCharacteristicValues.add(value);
        prodSpecCharValues=configSpecChar.retrieveValue(time);
        assertEquals("query the value of Charistist:(Time points in two time periods.)", 2, prodSpecCharValues.size());
        assertEquals("query the value of Charistist:(Time points in two time periods.)", exceptProductSpecCharacteristicValues, prodSpecCharValues);

        List<ProductSpecCharacteristicValue> exceptCharValues=new ArrayList<ProductSpecCharacteristicValue>();
        prodSpecCharValues=configSpecChar.retrieveValue(format.parse("2015-07-22 12:00:00"));
        value=new ProductSpecCharacteristicValue(ProdSpecEnum.ProdSpecType.NUMERIC.getName(),false,"GHz",validFor,"2.7");
        exceptCharValues.add(value);
        assertEquals("query the value of Charistist:(Time points in on time periods.)", 1, prodSpecCharValues.size());
        assertEquals("query the value of Charistist:(Time points in on time periods.)",exceptCharValues,prodSpecCharValues);

        exceptProductSpecCharacteristicValues=new ArrayList<ProductSpecCharacteristicValue>();
        prodSpecCharValues=configSpecChar.retrieveValue(format.parse("2015-01-03 12:00:00"));
        assertEquals("query the value of Charistist:(Time points not in on time periods.)",0,prodSpecCharValues.size());
        assertEquals("query the value of Charistist:(Time points not in on time periods.)",exceptProductSpecCharacteristicValues,prodSpecCharValues);
    }

    @Test
    public void testAssociate() throws Exception {
        ProductSpecCharacteristic targetChar = null;
        List<ProductSpecCharRelationship>  exceptProductSpecRelationship =new ArrayList<ProductSpecCharRelationship>();
        int result = CommonErrorCode.SUCCESS.getCode();
        result=prodSpecChar.associate(targetChar, ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getName(), validFor);
        assertEquals("add a empty targetProdSpec",ProdSpecErrorCode.PROD_SPEC_CHAR_IS_NULL.getCode(),result);
        assertEquals("add a empty targetProdSpec",exceptProductSpecRelationship, prodSpecChar.getProdSpecCharRelationship());

        targetChar = new ProductSpecCharacteristic("2", "Size and weight", ProdSpecEnum.ProdSpecType.NUMERIC.getName(), validFor, "true", 1, 1, true, "compistchar", "");
        result = prodSpecChar.associate(targetChar, null, validFor);
        assertEquals("add a empty type ",ProdSpecErrorCode.PROD_SPEC_CHAR_RELATIONSHIP_TYPE_IS_NULL.getCode(),result);
        assertEquals("add a empty type",exceptProductSpecRelationship, prodSpecChar.getProdSpecCharRelationship());


        result=prodSpecChar.associate(prodSpecChar, ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getName(), validFor);
        assertEquals("add Related SpecChar:The srcChar is the same as the targetChar.",ProdSpecErrorCode.PROD_SPEC_CHAR_EQUALS_TO_CURRENT.getCode(), result);
        assertEquals("add Related SpecChar:The srcChar is the same as the targetChar.",exceptProductSpecRelationship, prodSpecChar.getProdSpecCharRelationship());

        result=prodSpecChar.associate(targetChar, ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), validFor);
        ProductSpecCharRelationship productSpecCharValueRelationShip = new ProductSpecCharRelationship(prodSpecChar, targetChar, ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), validFor);
        exceptProductSpecRelationship.add(productSpecCharValueRelationShip);
        assertEquals("add Related SpecChar",  CommonErrorCode.SUCCESS.getCode(), result);
        assertEquals("add Related SpecChar.", 1, prodSpecChar.getProdSpecCharRelationship().size());
        assertEquals("add Related SpecChar.",exceptProductSpecRelationship,prodSpecChar.getProdSpecCharRelationship());

        result=prodSpecChar.associate(targetChar, ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), validFor);
        assertEquals("add duplicat Relationship",ProdSpecErrorCode.PROD_SPEC_CHAR_HAS_RELATED_TO_CURRENT.getCode(), result);
        assertEquals("add duplicat Relationship.",1, prodSpecChar.getProdSpecCharRelationship().size());
        assertEquals("add duplicat Relationship.",exceptProductSpecRelationship,prodSpecChar.getProdSpecCharRelationship());

        result=prodSpecChar.associate(targetChar, ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), new TimePeriod(DateUtils.datetimeFormat.parse("2015-01-01 00:00:00"), DateUtils.datetimeFormat.parse("2015-01-29 23:59:59")));
        productSpecCharValueRelationShip = new ProductSpecCharRelationship(prodSpecChar, targetChar, ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), new TimePeriod(DateUtils.datetimeFormat.parse("2015-01-01 00:00:00"),DateUtils.datetimeFormat.parse("2015-01-29 23:59:59")));
        exceptProductSpecRelationship.add(productSpecCharValueRelationShip);
        assertEquals("add Related SpecChar£ºhave create a aggregation relationship (time before )", CommonErrorCode.SUCCESS.getCode(), result);
        assertEquals("add Related SpecChar.", 2, prodSpecChar.getProdSpecCharRelationship().size());
        assertEquals("add Related SpecChar.",exceptProductSpecRelationship, prodSpecChar.getProdSpecCharRelationship());

        result=prodSpecChar.associate(targetChar, ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), new TimePeriod(DateUtils.datetimeFormat.parse("2015-01-01 00:00:00"), DateUtils.datetimeFormat.parse("2015-06-29 23:59:59")));
        assertEquals("add Related SpecChar£ºhave create a aggregation relationship (time in period  )",ProdSpecErrorCode.PROD_SPEC_CHAR_HAS_RELATED_TO_CURRENT.getCode(), result);
        assertEquals("add Related SpecChar£ºhave create a aggregation relationship (time in period  )",2, prodSpecChar.getProdSpecCharRelationship().size());
        assertEquals("add Related SpecChar£ºhave create a aggregation relationship (time in period  )",exceptProductSpecRelationship, prodSpecChar.getProdSpecCharRelationship());

        result=prodSpecChar.associate(targetChar, ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), new TimePeriod(DateUtils.datetimeFormat.parse("2015-09-01 23:59:59"), DateUtils.datetimeFormat.parse("2015-10-29 23:59:59")));
        productSpecCharValueRelationShip = new ProductSpecCharRelationship(prodSpecChar, targetChar, ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), new TimePeriod(DateUtils.datetimeFormat.parse("2015-09-01 23:59:59"),DateUtils.datetimeFormat.parse("2015-10-29 23:59:59")));
        exceptProductSpecRelationship.add(productSpecCharValueRelationShip);
        assertEquals("add Related SpecChar£ºhave create a aggregation relationship (time after  )", CommonErrorCode.SUCCESS.getCode(), result);
        assertEquals("add Related SpecChar.have create a aggregation relationship (time after  )",3, prodSpecChar.getProdSpecCharRelationship().size());
        assertEquals("add Related SpecChar£ºhave create a aggregation relationship (time after  )",exceptProductSpecRelationship, prodSpecChar.getProdSpecCharRelationship());

        result=prodSpecChar.associate(targetChar, ProdSpecEnum.ProdSpecRelationship.DEPENDENCY.getValue(), validFor);
        assertEquals("add Related SpecChar:have create other relationship", ProdSpecErrorCode.PROD_SPEC_CHAR_HAS_RELATED_TO_CURRENT.getCode(), result);
        assertEquals("add Related SpecChar£ºhave create other relationship",3, prodSpecChar.getProdSpecCharRelationship().size());
        assertEquals("add Related SpecChar£ºhave create a aggregation relationship (time after  )",exceptProductSpecRelationship, prodSpecChar.getProdSpecCharRelationship());
    }

    @Test
    public void testRetrieveRelatedCharacteristic() throws Exception {
        String relationType=null;
        Date time=DateUtils.datetimeFormat.parse("2015-06-11 00:00:00");
        List<ProductSpecCharacteristic> exceptProdSpecChar = new ArrayList<ProductSpecCharacteristic>();
        List<ProductSpecCharacteristic> characteristics = null;
        try{
            prodSpecChar.retrieveRelatedCharacteristic(relationType,time);
            fail("relationType is null ,excepted a IllargumentException for relationType");
        }catch (IllegalArgumentException ex){

        }
        try{
            relationType=ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getName();
            prodSpecChar.retrieveRelatedCharacteristic(relationType,null);
            fail("time is null ,excepted a IllargumentException for time");
        }catch (IllegalArgumentException ex){

        }
        characteristics= prodSpecChar.retrieveRelatedCharacteristic(relationType, time);
        assertEquals("The char does not specify the type of association char ",exceptProdSpecChar,characteristics);

        prodSpecChar.associate(configSpecChar, ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getName(), validFor, 1);
        characteristics=prodSpecChar.retrieveRelatedCharacteristic(ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getName());
        exceptProdSpecChar.add(configSpecChar);
        assertEquals("The char have the specify   type of association char", 1, characteristics.size());
        assertEquals("The char have the specify   type of association char",exceptProdSpecChar,characteristics);

    }
}
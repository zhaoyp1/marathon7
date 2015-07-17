package com.digiwes.product.spec;

import com.digiwes.basetype.TimePeriod;
import com.digiwes.common.enums.CommonErrorCode;
import com.digiwes.common.enums.ProdSpecEnum;
import com.digiwes.common.enums.ProdSpecErrorCode;
import com.digiwes.utils.DateUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by zhaoyp on 2015/7/16.
 */
public class ProductSpecCharacteristicValueTest {
    private ProductSpecCharacteristicValue memoryCharValue;
    private ProductSpecCharacteristicValue charValue;
    private static TimePeriod validFor;
    @Before
    public void setUp() throws Exception {
        memoryCharValue=new ProductSpecCharacteristicValue(ProdSpecEnum.ProdSpecType.NUMERIC.getName(),false,"GHz",validFor,"2.7");
        charValue=new ProductSpecCharacteristicValue(ProdSpecEnum.ProdSpecType.NUMERIC.getName(),false,"GHz",validFor,"2.9");

    }
    @BeforeClass
    public static void setUpBeforeClass()throws ParseException{
        validFor = new TimePeriod(DateUtils.datetimeFormat.parse("2015-02-03 12:00:00"),DateUtils.datetimeFormat.parse("2015-07-21 23:59:59"));

    }
    @Test
    public void testAssociate() throws Exception {
        int result = CommonErrorCode.SUCCESS.getCode();
        List<ProdSpecCharValueRelationship> exceptProdSpecCharValueRelationships = new ArrayList<ProdSpecCharValueRelationship>();
        result=memoryCharValue.associate(null, ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getName(), validFor);
        assertEquals("add a related charValue,charValue is null", ProdSpecErrorCode.PROD_SPEC_CHAR_IS_NULL.getCode(), result);

        result=memoryCharValue.associate(charValue,null, validFor);
        assertEquals("add a related charValue,relationType is null",ProdSpecErrorCode.PROD_SPEC_CHAR_VALUE_RELATIONSHIP_TYPE_IS_NULL.getCode(), result);

        result=memoryCharValue.associate(memoryCharValue, ProdSpecEnum.ProdSpecRelationship.EXCLUSIBITY.getName(), validFor);
        assertEquals("add a related charValue,the specify value is same with currentCharValue", ProdSpecErrorCode.PROD_SPEC_CHAR_VALUE_EQUALS_TO_CURRENT.getCode(), result);
        assertEquals("add a related charValue,the specify value is same with currentCharValue", exceptProdSpecCharValueRelationships, memoryCharValue.getProdSpecCharValueRelationship());

        result=memoryCharValue.associate(charValue, ProdSpecEnum.ProdSpecRelationship.EXCLUSIBITY.getName(), validFor);
        ProdSpecCharValueRelationship specCharValueRelationShip = new ProdSpecCharValueRelationship(memoryCharValue,charValue, ProdSpecEnum.ProdSpecRelationship.EXCLUSIBITY.getName(), validFor);
        exceptProdSpecCharValueRelationships.add(specCharValueRelationShip);
        assertEquals("add a related charValue", CommonErrorCode.SUCCESS.getCode(), result);
        assertEquals("add a related charValue", 1, memoryCharValue.getProdSpecCharValueRelationship().size());
        assertEquals("add a related charValue",exceptProdSpecCharValueRelationships, memoryCharValue.getProdSpecCharValueRelationship());

        result=memoryCharValue.associate(charValue, ProdSpecEnum.ProdSpecRelationship.EXCLUSIBITY.getName(), validFor);
        assertEquals("add a related charValue,have created a exclusive relationship", ProdSpecErrorCode.PROD_SPEC_CHAR_VALUE_HAS_RELATED_TO_CURRENT.getCode(), result);
        assertEquals("add a related charValue,have created a exclusive relationship", 1, memoryCharValue.getProdSpecCharValueRelationship().size());
        assertEquals("add a related charValue,have created a exclusive relationship",exceptProdSpecCharValueRelationships, memoryCharValue.getProdSpecCharValueRelationship());

        result=memoryCharValue.associate(charValue, ProdSpecEnum.ProdSpecRelationship.EXCLUSIBITY.getName(), new TimePeriod(DateUtils.datetimeFormat.parse("2015-01-01 00:00:00"),DateUtils.datetimeFormat.parse("2015-01-29 23:59:59")));
        specCharValueRelationShip = new ProdSpecCharValueRelationship(memoryCharValue,charValue, ProdSpecEnum.ProdSpecRelationship.EXCLUSIBITY.getName(), new TimePeriod(DateUtils.datetimeFormat.parse("2015-01-01 00:00:00"),DateUtils.datetimeFormat.parse("2015-01-29 23:59:59")));
        exceptProdSpecCharValueRelationships.add(specCharValueRelationShip);
        Assert.assertEquals("add Related charValuehave create a aggregation relationship (time before )", CommonErrorCode.SUCCESS.getCode(), result);
        Assert.assertEquals("add Related charValue.", 2, memoryCharValue.getProdSpecCharValueRelationship().size());
        Assert.assertEquals("add Related charValue.", exceptProdSpecCharValueRelationships, memoryCharValue.getProdSpecCharValueRelationship());

        result=memoryCharValue.associate(charValue, ProdSpecEnum.ProdSpecRelationship.EXCLUSIBITY.getName(), new TimePeriod(DateUtils.datetimeFormat.parse("2015-01-01 00:00:00"), DateUtils.datetimeFormat.parse("2015-06-29 23:59:59")));
        assertEquals("add Related charValuehave create a aggregation relationship (time in period  )", ProdSpecErrorCode.PROD_SPEC_CHAR_VALUE_HAS_RELATED_TO_CURRENT.getCode(), result);
        assertEquals("add Related charValue.", 2, memoryCharValue.getProdSpecCharValueRelationship().size());
        assertEquals("add Related charValue.", exceptProdSpecCharValueRelationships, memoryCharValue.getProdSpecCharValueRelationship());

        specCharValueRelationShip = new ProdSpecCharValueRelationship(memoryCharValue,charValue, ProdSpecEnum.ProdSpecRelationship.EXCLUSIBITY.getName(),  new TimePeriod(DateUtils.datetimeFormat.parse("2015-09-01 23:59:59"), DateUtils.datetimeFormat.parse("2015-10-29 23:59:59")));
        exceptProdSpecCharValueRelationships.add(specCharValueRelationShip);
        result=memoryCharValue.associate(charValue, ProdSpecEnum.ProdSpecRelationship.EXCLUSIBITY.getName(), new TimePeriod(DateUtils.datetimeFormat.parse("2015-09-01 23:59:59"), DateUtils.datetimeFormat.parse("2015-10-29 23:59:59")));
        Assert.assertEquals("add Related charValuehave create a aggregation relationship (time after  )", CommonErrorCode.SUCCESS.getCode(), result);
        Assert.assertEquals("add Related charValue.", 3, memoryCharValue.getProdSpecCharValueRelationship().size());
        Assert.assertEquals("add Related charValue.", exceptProdSpecCharValueRelationships, memoryCharValue.getProdSpecCharValueRelationship());

        result=memoryCharValue.associate(charValue, ProdSpecEnum.ProdSpecRelationship.DEPENDENCY.getName(), validFor);
        assertEquals("add a related charValue,have created a exclusive relationship",ProdSpecErrorCode.PROD_SPEC_CHAR_VALUE_HAS_RELATED_TO_CURRENT.getCode(), result);
        assertEquals("add a related charValue,have created a exclusive relationship",charValue, memoryCharValue.getProdSpecCharValueRelationship().iterator().next().getProductSpecCharacteristicValue());
        assertEquals("add a related charValue,have created a exclusive relationship", exceptProdSpecCharValueRelationships, memoryCharValue.getProdSpecCharValueRelationship());
    }

    @Test
    public void testRetrieveRelatedCharValue() throws Exception {
        List<ProductSpecCharacteristicValue> charValues = null;
        List<ProductSpecCharacteristicValue> exceptCharValues=new ArrayList<ProductSpecCharacteristicValue>();
        try{
            charValues=memoryCharValue.retrieveRelatedCharValue(null, null);
            fail("reterive the relation charValues,excepted illegalArgumentException for parameter");
        }catch (IllegalArgumentException ex){
        }

        charValues=memoryCharValue.retrieveRelatedCharValue(ProdSpecEnum.ProdSpecRelationship.EXCLUSIBITY.getName(), new Date());
        assertEquals("Reterive the related CharValue,The current charValue and the specified charValue have no  created an exclusivity relationship",0,charValues.size());

        exceptCharValues.add(charValue);
        memoryCharValue.associate(charValue, ProdSpecEnum.ProdSpecRelationship.EXCLUSIBITY.getName(), validFor);
        charValues=memoryCharValue.retrieveRelatedCharValue(ProdSpecEnum.ProdSpecRelationship.EXCLUSIBITY.getName(), new Date());
        assertEquals("Reterive the related CharValue,The current charValue and the specified charValue have  created an exclusivity relationship", 1, charValues.size());
        assertEquals("Reterive the related CharValue,The current charValue and the specified charValue have  created an exclusivity relationship",exceptCharValues,charValues);

        charValues=memoryCharValue.retrieveRelatedCharValue(ProdSpecEnum.ProdSpecRelationship.DEPENDENCY.getName(), new Date());
        assertEquals("Reterive the related CharValue,The current charValue and the specified charValue have created an exclusivity relationship",0,charValues.size());
    }
}
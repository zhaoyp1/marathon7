package com.digiwes.product.spec;

import com.digiwes.basetype.TimePeriod;
import com.digiwes.common.enums.CommonErrorCode;
import com.digiwes.common.enums.ProdSpecEnum;
import com.digiwes.common.enums.ProdSpecErrorCode;
import com.digiwes.utils.DateUtils;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by liurl3 on 2015/7/16.
 */
public class ProductSpecificationTest extends TestCase {
    private  ProductSpecification atomicProdSpec;
    private  TimePeriod validFor;
    @Before
    public void setUp() throws ParseException {
        validFor = new TimePeriod(DateUtils.datetimeFormat.parse("2015-02-10 23:59:59"),DateUtils.datetimeFormat.parse("2015-11-01 23:59:59"));
        atomicProdSpec = new AtomicProductSpecification("1", "11 Pounds MacBook Air", "apple", "Mac", validFor);
    }

    @Test
    public void testAttachCharacteristic() throws Exception {
        ProductSpecCharacteristic specChar = new ProductSpecCharacteristic("1", "color", "1", validFor, "unique", 1, 3, false, "description", "derivationFormula");
        List<ProductSpecCharUse> expectCharUse = new ArrayList<ProductSpecCharUse>();
        int errorCode = atomicProdSpec.attachCharacteristic("Color", null, false, false,validFor);
        assertEquals("add a characteristic but it's null ,compare to return errorCode.", ProdSpecErrorCode.PROD_SPEC_CHAR_IS_NULL.getCode(), errorCode);
        assertEquals("add a characteristic but it's null ,expectCharUse compare to atomicProdSpec's characteristic.", expectCharUse, atomicProdSpec.getProdSpecChar());

        errorCode = atomicProdSpec.attachCharacteristic(null, specChar, false, false,validFor);
        assertEquals("add a characteristic but it's name null ,compare to return errorCode.", ProdSpecErrorCode.PROD_SPEC_CHAR_USE_NAME_IS_NULL_OR_EMPTY.getCode(), errorCode);
        assertEquals("add a characteristic but it's name null ,expectCharUse compare to atomicProdSpec's characteristic.", expectCharUse, atomicProdSpec.getProdSpecChar());

        errorCode = atomicProdSpec.attachCharacteristic("", specChar, false, false,validFor);
        assertEquals("add a characteristic but it's name is empty ,compare to return errorCode.", ProdSpecErrorCode.PROD_SPEC_CHAR_USE_NAME_IS_NULL_OR_EMPTY.getCode(), errorCode);
        assertEquals("add a characteristic but it's name is empty ,expectCharUse compare to atomicProdSpec's characteristic.", expectCharUse, atomicProdSpec.getProdSpecChar());

        ProductSpecCharUse charUse = new ProductSpecCharUse( specChar,"Color", false, false,validFor);
        expectCharUse.add(charUse);
        errorCode = atomicProdSpec.attachCharacteristic("Color", specChar, false, false,validFor);
        assertEquals("add a characteristic  ,compare to return errorCode.", CommonErrorCode.SUCCESS.getCode(), errorCode);
        assertEquals("add a characteristic ,expectCharUse compare to atomicProdSpec's characteristic.", expectCharUse, atomicProdSpec.getProdSpecChar());

        ProductSpecCharacteristic specChar2 = new ProductSpecCharacteristic("1", "color", "1", validFor, "unique", 1, 3, false, "description", "derivationFormula");
        errorCode = atomicProdSpec.attachCharacteristic("Color", specChar2, false, false,validFor);
        assertEquals("add an exists characteristic  ,compare to return errorCode.", ProdSpecErrorCode.PROD_SPEC_CHAR_HAS_ATTACHED_TO_SPEC.getCode(), errorCode);
        assertEquals("add an exists characteristic ,expectCharUse compare to atomicProdSpec's characteristic.", expectCharUse, atomicProdSpec.getProdSpecChar());

        ProductSpecCharUse charUse2 = new ProductSpecCharUse( specChar2,"Colours", false, false,validFor);
        expectCharUse.add(charUse2);
        errorCode = atomicProdSpec.attachCharacteristic("Colours", specChar2, false, false,validFor);
        assertEquals("add an exists characteristic but the charName is different ,compare to return errorCode.",CommonErrorCode.SUCCESS.getCode(), errorCode);
        assertEquals("add an exists characteristic  but the charName is different ,expectCharUse compare to atomicProdSpec's characteristic.", expectCharUse, atomicProdSpec.getProdSpecChar());

    }

    @Test
    public void testAssignCharacteristicValue() throws Exception {
        ProductSpecCharacteristic specChar = new ProductSpecCharacteristic("1", "color", ProdSpecEnum.ProdSpecType.TEXT.getName(), validFor, "unique", 1, 3, false, "description", "derivationFormula");
        ProductSpecCharacteristic specChar2 = new ProductSpecCharacteristic("2", "color", ProdSpecEnum.ProdSpecType.TEXT.getName(), validFor, "unique", 1, 3, false, "description", "derivationFormula");
        ProductSpecCharacteristicValue charValue = new ProductSpecCharacteristicValue(ProdSpecEnum.ProdSpecType.TEXT.getName(),false, "GBK", validFor, "red");
        ProductSpecCharacteristicValue charValue2 = new ProductSpecCharacteristicValue(ProdSpecEnum.ProdSpecType.TEXT.getName(),false, "GBK", validFor, "green");
        specChar.assignValue(charValue);
        specChar2.assignValue(charValue2);
        atomicProdSpec.attachCharacteristic("color", specChar, false, false, validFor);

        ProductSpecCharUse charUse = new ProductSpecCharUse( specChar,"Color", false, false,validFor);
        List<ProdSpecCharValueUse> expectCharValueUse =  new ArrayList<ProdSpecCharValueUse>();
        charUse.setProdSpecCharValue(expectCharValueUse);

        int errorCode = atomicProdSpec.assignCharacteristicValue("color", specChar, null, false, validFor);
        assertEquals("add a null to characteristic ,compare to return errorCode.",ProdSpecErrorCode.PROD_SPEC_CHAR_VALUE_IS_NULL.getCode(),errorCode);
        assertEquals("add a null to characteristic ,expectCharValueUse compare to charUse's value.", expectCharValueUse, atomicProdSpec.getProdSpecChar().get(0).getProdSpecCharValue());

        errorCode = atomicProdSpec.assignCharacteristicValue("color", null, charValue, false, validFor);
        assertEquals("add a value to characteristic but the characteristic is null,compare to return errorCode.",ProdSpecErrorCode.PROD_SPEC_CHAR_IS_NULL.getCode(),errorCode);
        assertEquals("add a value to characteristic but the characteristic is null ,expectCharValueUse compare to charUse's value.", expectCharValueUse, atomicProdSpec.getProdSpecChar().get(0).getProdSpecCharValue());

        errorCode = atomicProdSpec.assignCharacteristicValue("", specChar, charValue, false, validFor);
        assertEquals("add a value to characteristic but the characteristic's name is empty,compare to return errorCode.",ProdSpecErrorCode.PROD_SPEC_CHAR_USE_NAME_IS_NULL_OR_EMPTY.getCode(),errorCode);
        assertEquals("add a value to characteristic but the characteristic's name is empty ,expectCharValueUse compare to charUse's value.", expectCharValueUse, atomicProdSpec.getProdSpecChar().get(0).getProdSpecCharValue());

         errorCode = atomicProdSpec.assignCharacteristicValue("color", specChar2, charValue2, false, validFor);
        assertEquals("add a value to characteristic but the characteristic is not belong the ProdSpec ,compare to return errorCode.",ProdSpecErrorCode.PROD_SPEC_NOT_USED_CURRENT_CHAR.getCode(),errorCode);
        assertEquals("add a value to characteristic but the characteristic is not belong the ProdSpec ,expectCharValueUse compare to charUse's value.", expectCharValueUse, atomicProdSpec.getProdSpecChar().get(0).getProdSpecCharValue());

        errorCode = atomicProdSpec.assignCharacteristicValue("color", specChar, charValue2, false, validFor);
        assertEquals("add a value to characteristic but the value is not belong the characteristic ,compare to return errorCode.",ProdSpecErrorCode.PROD_SPEC_CHAR_NOT_INCLUDE_VALUE.getCode(),errorCode);
        assertEquals("add a value to characteristic but the value is not belong the characteristic ,expectCharValueUse compare to charUse's value.", expectCharValueUse, atomicProdSpec.getProdSpecChar().get(0).getProdSpecCharValue());

        ProdSpecCharValueUse valueUse = new ProdSpecCharValueUse(charValue,false,validFor);
        expectCharValueUse.add(valueUse);
        errorCode = atomicProdSpec.assignCharacteristicValue("color", specChar, charValue, false, validFor);
        assertEquals("add an value to characteristic ,compare to return errorCode.", CommonErrorCode.SUCCESS.getCode(),errorCode);
        assertEquals("add an value to characteristic ,expectCharValueUse compare to charUse's value.",expectCharValueUse,atomicProdSpec.getProdSpecChar().get(0).getProdSpecCharValue());

    }

    @Test
    public void testRetrieveCharacteristic() throws Exception {
        ProductSpecCharacteristic specChar = new ProductSpecCharacteristic("1", "color", ProdSpecEnum.ProdSpecType.TEXT.getName(), validFor, "unique", 1, 3, false, "description", "derivationFormula");
        ProductSpecCharacteristic specChar2 = new ProductSpecCharacteristic("2", "color", ProdSpecEnum.ProdSpecType.TEXT.getName(), validFor, "unique", 1, 3, false, "description", "derivationFormula");
        ProductSpecCharacteristicValue charValue = new ProductSpecCharacteristicValue(ProdSpecEnum.ProdSpecType.TEXT.getName(),false, "GBK", validFor, "red");
        ProductSpecCharacteristicValue charValue2 = new ProductSpecCharacteristicValue(ProdSpecEnum.ProdSpecType.TEXT.getName(),false, "GBK", validFor, "green");
        TimePeriod validFor2 = new TimePeriod(DateUtils.datetimeFormat.parse("2015-08-10 23:59:59"),DateUtils.datetimeFormat.parse("2015-09-01 23:59:59"));
        specChar.assignValue(charValue);
        specChar2.assignValue(charValue2);
        atomicProdSpec.attachCharacteristic("Color", specChar, false, false, validFor);
        atomicProdSpec.attachCharacteristic("Colour", specChar2, false, false, validFor2);
        List<ProductSpecCharUse> expectCharUse = new ArrayList<ProductSpecCharUse>();
        List<ProductSpecCharUse> charUseList = null;
        try{
            charUseList = atomicProdSpec.retrieveCharacteristic(null);
            fail("retrieve a characteristic ,expectCharUse compare to retrieved charUseList.");
        }   catch (IllegalArgumentException ex) {

        }
         try{
            Date time = null;
            charUseList = atomicProdSpec.retrieveCharacteristic(null);
            fail("retrieve characteristics by time but the parameter time is null.");
        }catch(IllegalArgumentException E){}

        charUseList = atomicProdSpec.retrieveCharacteristic(DateUtils.datetimeFormat.parse("2015-01-15 23:59:59"));
        assertEquals("retrieve prodSpec's characteristics is validFor before ,expectCharUse compare to retrieved charUseList.", expectCharUse, charUseList);

        charUseList = atomicProdSpec.retrieveCharacteristic(DateUtils.datetimeFormat.parse("2015-12-15 23:59:59"));
        assertEquals("retrieve prodSpec's characteristics is validFor after ,expectCharUse compare to retrieved charUseList.", expectCharUse, charUseList);

        ProductSpecCharUse charUse = new ProductSpecCharUse( specChar,"Color", false, false,validFor);
        expectCharUse.add(charUse);
        charUseList = atomicProdSpec.retrieveCharacteristic(new Date());
        assertEquals("retrieve currently valid characteristic ,expectCharUse compare to retrieved charUseList.", expectCharUse, charUseList);


    }

    @Test
    public void testRetrieveCharacteristicValue() throws Exception {
        ProductSpecCharacteristic specChar = new ProductSpecCharacteristic("1", "color", ProdSpecEnum.ProdSpecType.TEXT.getName(), validFor, "unique", 1, 3, false, "description", "derivationFormula");
        ProductSpecCharacteristicValue charValue = new ProductSpecCharacteristicValue(ProdSpecEnum.ProdSpecType.TEXT.getName(),false, "GBK", validFor, "red");
        TimePeriod validFor2 = new TimePeriod(DateUtils.datetimeFormat.parse("2015-08-10 23:59:59"),DateUtils.datetimeFormat.parse("2015-09-01 23:59:59"));
        ProductSpecCharacteristicValue charValue2 = new ProductSpecCharacteristicValue(ProdSpecEnum.ProdSpecType.TEXT.getName(),false, "GBK", validFor2, "green");
        specChar.assignValue(charValue);
        specChar.assignValue(charValue2);
        atomicProdSpec.attachCharacteristic("Color", specChar, false, false, validFor);
        atomicProdSpec.assignCharacteristicValue("Color",specChar,charValue,false,validFor);
        List<ProdSpecCharValueUse> expectCharValueUse = new ArrayList<ProdSpecCharValueUse>();
        List<ProdSpecCharValueUse> charValueUses = null;
        try{
            charValueUses = atomicProdSpec.retrieveCharacteristicValue("Color", null, new Date());
            fail("retrieve a characteristic's values but the characteristic is null ,expectCharValueUse compare to retrieved charValueUses.");
        }   catch (IllegalArgumentException ex){

        }
        try{
            charValueUses = atomicProdSpec.retrieveCharacteristicValue("", specChar, new Date());
            fail("retrieve a characteristic's values but the characteristic's name is empty ,expectCharValueUse compare to retrieved charValueUses.");

        }   catch (IllegalArgumentException ex){

        }

        charValueUses = atomicProdSpec.retrieveCharacteristicValue("Color", specChar,DateUtils.datetimeFormat.parse("2015-01-15 23:59:59"));
        assertEquals("retrieve a characteristic's values are validFor before ,expectCharValueUse compare to retrieved charValueUses.", expectCharValueUse, charValueUses);

        charValueUses = atomicProdSpec.retrieveCharacteristicValue("Color", specChar,DateUtils.datetimeFormat.parse("2015-12-15 23:59:59"));
        assertEquals("retrieve a characteristic's values are validFor after ,expectCharValueUse compare to retrieved charValueUses.", expectCharValueUse, charValueUses);

        ProdSpecCharValueUse valueUse = new ProdSpecCharValueUse(charValue,false,validFor);
        expectCharValueUse.add(valueUse);

        charValueUses = atomicProdSpec.retrieveCharacteristicValue("Color", specChar, new Date());
        assertEquals("retrieve a characteristic's values ,expectCharValueUse compare to retrieved charValueUses.",expectCharValueUse,charValueUses);


    }

}
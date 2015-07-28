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
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zhaoyp on 2015/7/17.
 */
public class ProductSpecCharUseTest {
    private	ProductSpecCharUse pscu =null;
    private ProductSpecCharacteristic prodSpecChar=null;
    private static TimePeriod validFor;
    @BeforeClass
    public static void initVliadFor()throws ParseException{
        validFor = new TimePeriod(DateUtils.datetimeFormat.parse("2015-06-01 00:00:00"),DateUtils.datetimeFormat.parse("2015-08-01 23:59:59"));
    }
    @Before
    public void setUp() throws Exception {
        prodSpecChar = new ProductSpecCharacteristic("1","depth", ProdSpecEnum.ProdSpecType.NUMERIC.getName(), validFor, "false",  1,  1, true, "height","");
        ProductSpecCharacteristicValue prodSpecCharValue = new ProductSpecCharacteristicValue(ProdSpecEnum.ProdSpecType.NUMERIC.getName(),false, "cm", validFor, "12");
        prodSpecChar.assignValue(prodSpecCharValue);
        ProductSpecCharacteristicValue prodSpecCharValuee = new ProductSpecCharacteristicValue(ProdSpecEnum.ProdSpecType.NUMERIC.getName(), false,"cm", validFor, "12.3");
        prodSpecChar.assignValue(prodSpecCharValuee);
        pscu = new ProductSpecCharUse(prodSpecChar,"depth", false, false, validFor);

    }

    @Test
    public void testAssignValue() throws Exception {
        int result= CommonErrorCode.SUCCESS.getCode();
        List<ProdSpecCharValueUse> exceptProductSpecCharValue = new ArrayList<ProdSpecCharValueUse>();

        result=pscu.assignValue(null,false,validFor);
        assertEquals("add a empty value", ProdSpecErrorCode.PROD_SPEC_CHAR_VALUE_IS_NULL.getCode(),result);
        assertEquals("add a empty value",exceptProductSpecCharValue,pscu.getProdSpecCharValue());

        ProductSpecCharacteristicValue prodSpecCharValue = new ProductSpecCharacteristicValue("1",false, "cm", validFor, "12", "", "");
        result= pscu.assignValue(prodSpecCharValue, false, validFor);
        assertEquals("value is not belong of the char",ProdSpecErrorCode.PROD_SPEC_CHAR_NOT_INCLUDE_VALUE.getCode(),result);
        assertEquals("value is not belong of the char",exceptProductSpecCharValue,pscu.getProdSpecCharValue());

        prodSpecCharValue = new ProductSpecCharacteristicValue(ProdSpecEnum.ProdSpecType.NUMERIC.getName(),false,"cm", validFor, "12");
        result=pscu.assignValue(prodSpecCharValue, false, new TimePeriod(DateUtils.datetimeFormat.parse("2015-05-01 00:00:00"),DateUtils.datetimeFormat.parse("2015-09-01 23:59:59")));
        assertEquals("validFor of prodSpecCharValueUse  is not in prodSpecCharValue's validFor",ProdSpecErrorCode.PROD_SPEC_CHAR_VALUE_USE_NOT_IN_VALUE.getCode(),result);
        assertEquals("validFor of prodSpecCharValueUse  is not in prodSpecCharValue's validFor",exceptProductSpecCharValue,pscu.getProdSpecCharValue());

        exceptProductSpecCharValue.add(new ProdSpecCharValueUse(prodSpecCharValue, false, validFor)) ;
        result=pscu.assignValue(prodSpecCharValue, false, validFor);
        assertEquals("add value",CommonErrorCode.SUCCESS.getCode(),result);
        assertEquals("add value",1,pscu.getProdSpecCharValue().size());
        assertEquals("add value",exceptProductSpecCharValue,pscu.getProdSpecCharValue());

        result=pscu.assignValue(prodSpecCharValue, false, validFor);
        assertEquals("add a duplicate value",ProdSpecErrorCode.PROD_SPEC_CHAR_VALUE_HAS_BEEN_USED.getCode(),result);
        assertEquals("add a duplicate value", 1, pscu.getProdSpecCharValue().size());
        assertEquals("add a duplicate value",exceptProductSpecCharValue,pscu.getProdSpecCharValue());

        prodSpecCharValue = new ProductSpecCharacteristicValue(ProdSpecEnum.ProdSpecType.NUMERIC.getName(),false,"cm", validFor, "12");
        result=pscu.assignValue(prodSpecCharValue, false, validFor);
        assertEquals("add a duplicate value",ProdSpecErrorCode.PROD_SPEC_CHAR_VALUE_HAS_BEEN_USED.getCode(),result);
        assertEquals("add a duplicate value", 1, pscu.getProdSpecCharValue().size());
        assertEquals("add a duplicate value",exceptProductSpecCharValue,pscu.getProdSpecCharValue());
    }
}
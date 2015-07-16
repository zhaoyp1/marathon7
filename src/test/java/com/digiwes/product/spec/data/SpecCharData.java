package com.digiwes.product.spec.data;


import com.digiwes.basetype.TimePeriod;
import com.digiwes.common.enums.ProdSpecEnum;
import com.digiwes.product.spec.ProductSpecCharacteristic;
import com.digiwes.product.spec.ProductSpecCharacteristicValue;
import java.util.HashMap;
import java.util.Map;

public class SpecCharData {
    //charId, name, description, minCardinality, maxCardinality, extensible
    private static final int CHAR_ID = 0;
    private static final int CHAR_NAME = 1;
    private static final int CHAR_DESC = 2;
    private static final int CHAR_MIN_CARDINALITY = 3;
    private static final int CHAR_MAX_CARDINALITY = 4;
    private static final int CHAR_UNIQUE = 5;
    private static final int CHAR_EXTENSIBLE = 6;
    private static final int CHAR_VALUE_TYPE = 7;
    private    static Object [][] specChar = {
            {"1","������","cpu",1,2,"false",false, ProdSpecEnum.ProdSpecType.TEXT.getValue()},
            {"2","�ߴ�������","compositechar",1,1,"true",false," "},
            {"3","�߶�","high",1,1,"true",true,ProdSpecEnum.ProdSpecType.FORTH.getValue()},
            {"4","���","width",1,1,"true",true,ProdSpecEnum.ProdSpecType.NUMERIC.getValue()},
            {"5","���","height",1,1,"true",true,ProdSpecEnum.ProdSpecType.NUMERIC.getValue()},
            {"6","�洢�豸","cache",1,1,"true",true,ProdSpecEnum.ProdSpecType.TEXT.getValue()}
    };

    //valueId,valueType,isDefault,unitOfMeasure,value, valueform,valueto,rangeInterval
    private static final  int VALUE_ID = 0;
    private static final  int VALUE_TYPE = 1;
    private static final int VALUE_DEFAULT_FLAG = 2;
    private static final  int VALUE_MEASURE = 3;
    private static final  int VALUE_VALUE = 4;
    private static final  int VALUE_VALUE_FROM = 4;
    private static final  int VALUE_VALUE_TO = 5;
    private static final  int VALUE_RANGEINTERVAL = 6;
    private static Object [][] specCharValue = {
            {"11",ProdSpecEnum.ProdSpecType.TEXT.getValue(),false,"GHz","1.6"},
            {"12",ProdSpecEnum.ProdSpecType.TEXT.getValue(),true,"GHz","2.0"},
            {"31",ProdSpecEnum.ProdSpecType.FORTH.getValue(),true,"cm",0.3,1.7,1},
            {"41",ProdSpecEnum.ProdSpecType.NUMERIC.getValue(),true,"cm",1.08},
            {"51",ProdSpecEnum.ProdSpecType.NUMERIC.getValue(),true,"cm",19.2},
            {"61",ProdSpecEnum.ProdSpecType.TEXT.getValue(),true,"GB",128},
            {"62",ProdSpecEnum.ProdSpecType.TEXT.getValue(),true,"GB",256},
            {"63",ProdSpecEnum.ProdSpecType.TEXT.getValue(),false,"GB",512}
    };
    //charId, valueId
    private static final int CHAR_REL_VAL_CHAR_ID = 0;
    private static final int CHAR_REL_VAL_VALUE_ID = 1;
    private static String [][] specCharRelateValue = {
            {"1","11"},
            {"1","12"},
            {"3","31"},
            {"4","41"},
            {"5","51"},
            {"6","61"},
            {"6","62"},
            {"6","63"}
    };
    //charId, relatedCharId, relationshipType
    private static final int CHAR_REL_CHAR_ID = 0;
    private static final int CHAR_REL_RELATED_CHAR_ID = 1;
    private static final int CHAR_REL_RELATIONSHIP_TYPE = 2;
    public	static String [][] specCharRelate = {
            {"2","3",ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue()},
            {"2","4",ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue()},
            {"2","5",ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue()}
    };
    private static Map<String, ProductSpecCharacteristic> charMap = new HashMap<String,ProductSpecCharacteristic>();
    private static  Map<String, ProductSpecCharacteristicValue> valueMap = new HashMap<String, ProductSpecCharacteristicValue>();
    private static TimePeriod validFor =  TimeUtil.creatTimePeriod("2015-06-01", "2015-08-01");
    private static  Boolean isInit = false;
    public static synchronized void init() {
        if (!isInit) {
            createValueMap();
            createCharMap();
            isInit = true;
        }
    }
    private static void createValueMap() {
        for (Object[] valueItem : specCharValue ) {
            ProductSpecCharacteristicValue prodSpecCharValue;
            if (ProdSpecEnum.ProdSpecType.FORTH.getValue().equals(valueItem[VALUE_TYPE].toString())) {
                prodSpecCharValue = new ProductSpecCharacteristicValue((String) valueItem[VALUE_TYPE],
                        (Boolean)valueItem[VALUE_DEFAULT_FLAG],
                        valueItem[VALUE_MEASURE].toString(),
                        validFor, valueItem[VALUE_VALUE_FROM].toString(),
                        valueItem[VALUE_VALUE_TO].toString(),
                        valueItem[VALUE_RANGEINTERVAL].toString());
            } else {
                prodSpecCharValue = new ProductSpecCharacteristicValue(
                        (String) valueItem[VALUE_TYPE],
                        (Boolean)valueItem[VALUE_DEFAULT_FLAG],
                        valueItem[VALUE_MEASURE].toString(),
                        validFor, valueItem[VALUE_VALUE].toString());
            }
            valueMap.put(valueItem[VALUE_ID].toString(), prodSpecCharValue);
        }
    }
    public  static  ProductSpecCharacteristicValue getValue(String id) {
        if (!isInit) {
            init();
        }
        return valueMap.get(id);
    }

    public  static ProductSpecCharacteristic getChar(String id) {
        if (!isInit) {
            init();
        }
        return charMap.get(id);
    }
    private static void createCharMap() {
        String derivationFormula = "";
        for ( Object[] charItem : specChar) {
            ProductSpecCharacteristic prodSpecChar = new ProductSpecCharacteristic(charItem[CHAR_ID].toString(),
                    charItem[CHAR_NAME].toString(),
                    charItem[CHAR_VALUE_TYPE].toString(),
                    validFor, charItem[CHAR_UNIQUE].toString(),
                    Integer.parseInt(charItem[CHAR_MIN_CARDINALITY].toString()),
                    Integer.parseInt(charItem[CHAR_MAX_CARDINALITY].toString()),
                    (Boolean)charItem[CHAR_EXTENSIBLE],
                    charItem[CHAR_DESC].toString(),
                    derivationFormula);
            charMap.put(charItem[CHAR_ID].toString(), prodSpecChar);
        }

        //attach value
        attachValue();

        //addRelatedCharacteristic
        addRelatedCharacteristic();
    }
    private static void attachValue() {
        for (String[] relatedItem : specCharRelateValue ) {
            ProductSpecCharacteristic prodSpecChar = charMap.get(relatedItem[CHAR_REL_VAL_CHAR_ID]);
            if (null != prodSpecChar) {
                ProductSpecCharacteristicValue value = valueMap.get(relatedItem[CHAR_REL_VAL_VALUE_ID]);
                if (null != value) {
                    prodSpecChar.assignValue(value);
                }
            }
        }
    }
    private static void addRelatedCharacteristic() {
        TimePeriod validFor =  TimeUtil.creatTimePeriod("2015-06-01", "2015-08-21");
        for (String[] relCharItem : specCharRelate) {
            ProductSpecCharacteristic psc  = charMap.get(relCharItem[CHAR_REL_CHAR_ID]);
            if (null != psc) {
                psc.associate(charMap.get(relCharItem[CHAR_REL_RELATED_CHAR_ID]),
                        relCharItem[CHAR_REL_RELATIONSHIP_TYPE], validFor);
            }
        }
    }

}


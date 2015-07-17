package com.digiwes.common.enums;

/**
 * Created by dongwh on 2015-7-16.
 */
public class ProdSpecEnum {

    /**
     * the status of char and charValue
     */
    public enum ProdSpecType{
        TEXT("1","TEXT"),
        NUMERIC("2","NUMERIC"),
        FORTH("3","FORTH");
        private String value;
        private String name;
        ProdSpecType(String value,String name){
            this.value = value;
            this.name = name;
        }
        public String getValue(){
            return this.value;
        }
        public String getName(){
            return this.name;
        }
    }

    /**
     * version level
     * */
    public enum VersionLevel{
        MAJOR_VERSION("1","MAJOR_VERSION"),
        MINOR_VERSION("2","MINOR_VERSION"),
        PATCH_VERSION("3","PATCH_VERSION");
        private String value;
        private String name;
        VersionLevel(String value,String name){
            this.value = value;
            this.name = name;
        }
        public String getValue(){
            return this.value;
        }
        public String getName(){
            return this.name;
        }
    }

    /**
     * the status of prodSpec
     */
    public enum ProdSpecStatus{
        STATUS_INACTIVE("0","STATUS_INACTIVE"),
        STATUS_ACTIVE("1","STATUS_ACTIVE"),
        STATUS_PLANED("2","STATUS_PLANED");
        private String value;
        private String name;
        ProdSpecStatus(String value,String name){
            this.value = value;
            this.name = name;
        }
        public String getValue(){
            return this.value;
        }
        public String getName(){
            return this.name;
        }
    }

    /**
     * type of relationship about prodSpec
     */
    public enum ProdSpecRelationship{
        AGGREGATION("1","AGGREGATION"),
        DEPENDENCY("2","DEPENDENCY"),
        MIGRATION("3","MIGRATION"),
        SUBSTITUTION("4","SUBSTITUTION"),
        EXCLUSIBITY("5","EXCLUSIBITY");
        private String value;
        private String name;
        ProdSpecRelationship(String value,String name){
            this.value = value;
            this.name = name;
        }
        public String getValue(){
            return this.value;
        }
        public String getName(){
            return this.name;
        }
    }

    /**
     * type of rangeInterval about charValue
     */
    public enum RangeInterval{

        OPEN("1","OPEN"),
        CLOSED("2","CLOSED"),
        CLOSED_BOTTOM("3","CLOSED_BOTTOM"),
        CLOSED_TOP("4","CLOSED_TOP");
        private String value;
        private String name;

        RangeInterval(String value,String name){
            this.value = value;
            this.name = name;
        }
        public String getValue(){
            return this.value;
        }
        public String getName(){
            return this.name;
        }
    }
}

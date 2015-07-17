package com.digiwes.common.enums;

public enum ProdOfferingEnum {
    ;/**
     * OfferingStatus
     */
    public enum ProductOfferingStatus {
        PLANNED("1", "PLANNED"),
        OBSOLETE("2", "OBSOLETE"),
        ACTIVE("3", "ACTIVE");

        private String value;
        private String name;

        /**
         * 
         * @param value
         * @param name
         */
        ProductOfferingStatus(String value, String name) {
            this.value = value;
            this.name = name;
        }

        public String getValue() {
            return this.value;
        }

        public String getName() {
            return this.name;
        }


    }/**
     * OfferingRelationshipType
     */
    public enum OfferingRelationshipType {
        AGGREGATION("1", "PARTNER"),
        DEPENDENCY("2", "EQUIVALENT"),
        MIGRATION("3", "ALTERNATE");

        /**
         * 
         * @param value
         * @param name
         */
        OfferingRelationshipType(String value, String name) {
            this.value = value;
            this.name = name;
        }

        private String value;
        private String name;

        public String getValue() {
            return this.value;
        }

        public String getName() {
            return this.name;
        }


    }

}
package com.digiwes.common.enums;

public enum ProdOfferingEnum {
    ;/**
     * OfferingStatus
     */
    public enum ProductOfferingStatus {
        PLANNED("PLANNED", "PLANNED"),
        OBSOLETE("OBSOLETE", "OBSOLETE"),
        ACTIVE("ACTIVE", "ACTIVE");

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

        /**
         * 
         * @param value
         */
        public  String getName(String value) {
            return this.name;
        }

    }/**
     * OfferingStatus
     */
    public enum OfferingRelationshipType {
        AGGREGATION("1", "AGGREGATION"),
        DEPENDENCY("2", "DEPENDENCY"),
        MIGRATION("3", "MIGRATION"),
        SUBSTITUTION("4", "SUBSTITUTION"),
        EXCLUSIVITY("5", "EXCLUSIVITY");

        private String value;
        private String name;

        /**
         * 
         * @param value
         * @param name
         */
        OfferingRelationshipType(String value, String name) {
            // TODO - implement OfferingRelationshipType.OfferingRelationshipType
            throw new UnsupportedOperationException();
        }

        public String getValue() {
            return this.value;
        }

        public String getName() {
            return this.name;
        }

        /**
         * 
         * @param value
         */
        public  String getName(String value) {
            return this.name;
        }

    }/**
     * OfferingStatus
     */
    public enum ProductCatalogType {
        WEB("1", "web"),
        BOOK("2", "book");

        private String value;
        private String name;

        /**
         * 
         * @param value
         * @param name
         */
        ProductCatalogType(String value, String name) {
            // TODO - implement ProductCatalogType.ProductCatalogType
            throw new UnsupportedOperationException();
        }

        public String getValue() {
            return this.value;
        }

        public String getName() {
            return this.name;
        }

        /**
         * 
         * @param value
         */
        public  String getName(String value) {
            return this.name;
        }

    }

}
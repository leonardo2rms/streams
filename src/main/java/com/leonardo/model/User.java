package com.leonardo.model;

import java.util.List;

public class User {
    private String name;
    private List<Integer> phoneNumbers;

    public User(String name, List<Integer> phoneNumbers) {
        this.name = name;
        this.phoneNumbers = phoneNumbers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<Integer> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public static class UserBuilder {
        private String name;
        private List<Integer> phoneNumbers;

        public UserBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder setPhoneNumbers(List<Integer> phoneNumbers) {
            this.phoneNumbers = phoneNumbers;
            return this;
        }

        public User createUser() {
            return new User(name, phoneNumbers);
        }
    }
}

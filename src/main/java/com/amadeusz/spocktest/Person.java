package com.amadeusz.spocktest;

class Person {

    private String name;
    private String sex;
    private int age;

    public Person(String name) {
        this.name = name;
        this.sex = name == "Fred" ? "Male" : "Female";
        this.age = name == "Franek" ? 16 : 25;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}

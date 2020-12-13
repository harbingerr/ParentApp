package com.example.parent;

public class Parent {

    public String parentId;
    public String childName1;
    public String childName2;
    public String childName3;

    public Parent(String parentId, String childName1) {
        this.parentId = parentId;
        this.childName1 = childName1;
    }

    public String getChildName2() {
        return childName2;
    }

    public void setChildName2(String childName2) {
        this.childName2 = childName2;
    }

    public String getChildName3() {
        return childName3;
    }

    public void setChildName3(String childName3) {
        this.childName3 = childName3;
    }
}

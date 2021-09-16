package com.example.mytest;

import java.util.Objects;

public class Police {
    private String name;
    private int plNo;

    public Police(String name, int plNo) {
        this.name = name;
        this.plNo = plNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlNo() {
        return plNo;
    }

    public void setPlNo(int plNo) {
        this.plNo = plNo;
    }

    @Override
    public String toString() {
        return "Police{" +
                "name='" + name + '\'' +
                ", plNo=" + plNo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Police police = (Police) o;
        return plNo == police.plNo && Objects.equals(name, police.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, plNo);
    }
}

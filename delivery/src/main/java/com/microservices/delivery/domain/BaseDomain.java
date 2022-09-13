package com.microservices.delivery.domain;

import java.util.Objects;

public abstract class BaseDomain {

    public abstract Long getId();
    public abstract void setId(Long id);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseDomain that = (BaseDomain) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

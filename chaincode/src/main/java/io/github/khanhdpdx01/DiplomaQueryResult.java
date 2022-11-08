package io.github.khanhdpdx01;

import com.owlike.genson.annotation.JsonProperty;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

import java.util.Objects;

@DataType()
public class DiplomaQueryResult {
    @Property()
    private final String key;
    @Property()
    private final Diploma record;


    public DiplomaQueryResult(@JsonProperty("key") String key,
                              @JsonProperty("record") Diploma record) {
        this.key = key;
        this.record = record;
    }

    public String getKey() {
        return key;
    }

    public Diploma getRecord() {
        return record;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiplomaQueryResult that = (DiplomaQueryResult) o;
        return Objects.equals(key, that.key) && Objects.equals(record, that.record);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, record);
    }

    @Override
    public String toString() {
        return "DiplomaQueryResult{" +
                "key='" + key + '\'' +
                ", record=" + record +
                '}';
    }
}

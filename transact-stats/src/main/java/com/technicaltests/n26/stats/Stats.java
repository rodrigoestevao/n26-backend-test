package com.technicaltests.n26.stats;

import java.io.Serializable;
import com.technicaltests.n26.transaction.Transaction;

public class Stats implements Serializable {
    private static final long serialVersionUID = 847241992970989671L;

    private Double sum;
    private Double max;
    private Double min;
    private Long count;

    public Stats() {
        this.sum = 0.0;
        this.max = 0.0;
        this.min = 0.0;
        this.count = 0L;
    }

    public Double getSum() {
        return sum;
    }

    public void plusSum(Double value) {
        this.sum += value;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = (max > this.max) ? max : this.max;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = (min > this.min) ? min : this.min;
    }

    public Long getCount() {
        return count;
    }

    public void incCount() {
        ++this.count;
    }

    public Double getAvg() {
        return (this.count > 0) ? this.sum / this.count : 0.0;
    }

    public Stats update(Transaction transaction) {
        if (transaction != null) {
            this.setMax(transaction.getAmount());
            this.setMin(transaction.getAmount());
            this.plusSum(transaction.getAmount());
            this.incCount();
        }
        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((count == null) ? 0 : count.hashCode());
        result = prime * result + ((max == null) ? 0 : max.hashCode());
        result = prime * result + ((min == null) ? 0 : min.hashCode());
        result = prime * result + ((sum == null) ? 0 : sum.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        final boolean equals;
        if (this == obj) {
            equals = true;
        } else if (obj == null) {
            equals = false;
        } else if (!(obj instanceof Stats)) {
            equals = false;
        } else {
            Stats other = (Stats) obj;
            if (((count == null) && (other.count != null)) || (!count.equals(other.count))) {
                equals = false;
            } else if (((max == null) && (other.max != null)) || (!max.equals(other.max))) {
                equals = false;
            } else if (((min == null) && (other.min != null)) || (!min.equals(other.min))) {
                equals = false;
            } else if (((sum == null) && (other.sum != null)) || (!sum.equals(other.sum))) {
                equals = false;
            } else {
                equals = true;
            }
        }
        return equals;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Stats [count=");
        builder.append(count);
        builder.append(", sum=");
        builder.append(sum);
        builder.append(", avg=");
        builder.append(getAvg());
        builder.append(", max=");
        builder.append(max);
        builder.append(", min=");
        builder.append(min);
        builder.append("]");
        return builder.toString();
    }

}

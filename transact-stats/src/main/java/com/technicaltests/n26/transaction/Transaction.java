package com.technicaltests.n26.transaction;

import java.io.Serializable;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class Transaction implements Serializable {
    private static final long serialVersionUID = 1312420229560053899L;

    private static final long INTERVAL_IN_SECONDS = 10L;

    private final double amount;

    private final long timestamp;

    public Transaction() {
        this.amount = 0.0;
        this.timestamp = 0L;
    }

    public double getAmount() {
        return amount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public boolean expired() {
        final long elapsed = Instant.now().minusMillis(this.timestamp).toEpochMilli();
        return TimeUnit.SECONDS.convert(elapsed, TimeUnit.MILLISECONDS) > INTERVAL_IN_SECONDS;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(amount);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + (int) (timestamp ^ (timestamp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        final boolean equals;
        if (this == obj) {
            equals = true;
        } else if (obj == null) {
            equals = false;
        } else if (!(obj instanceof Transaction)) {
            equals = false;
        } else {
            Transaction other = (Transaction) obj;
            if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount)) {
                equals = false;
            } else if (timestamp != other.timestamp) {
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
        builder.append("Transaction [amount=");
        builder.append(amount);
        builder.append(", timestamp=");
        builder.append(timestamp);
        builder.append("]");
        return builder.toString();
    }
}

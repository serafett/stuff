import com.hazelcast.core.PartitionAware;

import java.io.Serializable;

public final class OrderKey implements PartitionAware, Serializable {
	private final String orderId;
	private final String customerId;

	public OrderKey(String orderId, String customerId){
	    this.orderId = orderId;
	    this.customerId = customerId;
    }

    public Object getPartitionKey(){return customerId;}

    public int hashCode(){return orderId.hashCode();}

    public boolean equals(Object thatObject){
	    if(thatObject == this)return true;
	    if(!(thatObject instanceof OrderKey))return false;
	    OrderKey that = (OrderKey)thatObject;
	    return that.orderId.equals(this.orderId);
    }
}
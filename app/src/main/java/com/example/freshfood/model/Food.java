package com.example.freshfood.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.freshfood.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;


@Entity(tableName="food",
foreignKeys = @ForeignKey(
        entity = Store.class,
        parentColumns = "food_id",
        childColumns = "store_id"
))
public class Food implements Parcelable {


    @ColumnInfo(name="food_id")
    @PrimaryKey
private long foodId;

    @Expose
    @ColumnInfo(name="store_name")
    private Store store;

    @Expose
    @SerializedName("name")
    @ColumnInfo(name="name")
private String name;

    @Expose
    @SerializedName("amount")
    @ColumnInfo(name="amount")
private String amount;

    @Expose
    @SerializedName("food_type")
    @ColumnInfo(name="food_type")
    private FoodType foodType;

    @Expose
    @SerializedName("price")
    @ColumnInfo(name="price")
private float price;

    @Expose
    @SerializedName("imgSrc")
    @ColumnInfo(name="img_src")
    private int imgSrc;


    @Expose
    @SerializedName("is_available")
    @ColumnInfo(name="is_available")
    private boolean isAvailable;

    @Expose
    @SerializedName("discount_value")
    @ColumnInfo(name="discount_value")
    private String discountValue;

    @Expose
    @SerializedName("like_count")
    @ColumnInfo(name="like_count")
    private int likeCount;

    private List<Food> foodList;






    protected Food(Parcel parcel) {
        this.name = parcel.readString();
        this.store = parcel.readParcelable(Store.class.getClassLoader());
        this.amount = parcel.readString();
        this.foodType = FoodType.valueOf(parcel.readString());
        this.price = parcel.readFloat();
        this.imgSrc = parcel.readInt();
        this.isAvailable = parcel.readByte() == 1;
        this.discountValue = parcel.readString();
        this.likeCount = parcel.readInt();
    }

    public Food(Store store, String name, String amount, FoodType foodType, float price, int imgSrc, boolean isAvailable, String discountValue) {
        this.store = store;
        this.name = name;
        this.amount = amount;
        this.foodType = foodType;
        this.price = price;
        this.imgSrc = imgSrc;
        this.isAvailable = isAvailable;
        this.discountValue = discountValue;
        this.likeCount = 0;
    }

    public Food(Store store, FoodType foodType) {
        this.store = store;
        if(foodType == FoodType.VEGETABLE)
        this.foodList = store.getFoodList();


    }

    public Store getStore() {
        return store;
    }

    public String getName() {
        return name;
    }

    public String getAmount() {
        return amount;
    }

    public int getLikeCount() {
        return likeCount;
    }


    private int getDiscountValueFromString(String discountValueString) {
        if(discountValueString == null)
            return 0;
        if(discountValueString.length() == 3)
            return  Integer.parseInt(discountValueString.substring(0, 2));
        if(discountValueString.length() == 2)
            return Integer.parseInt(discountValueString.substring(0, 1));
        return 0;
    }
    public float getPrice() {
        int value = getDiscountValueFromString(discountValue);
        if(value > 0) {
            price = price - price * (value / 100);
        }
        return price;
    }

    public int getImgSrc() {
        return imgSrc;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getDiscountValue() {
        return discountValue;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public int getFoodDescription() {
        int resId = 0;
        switch(foodType) {
            case VEGETABLE:
                resId = R.string.vegetable;
                break;
            case FRUIT:
                resId = R.string.fruits;
                break;
            case SEAFOOD:
                resId = R.string.seafood;
                break;
        }
        return resId;
    }

    public int getColorResId() {
        int resId = 0;
       if(isAvailable) {
           resId = R.color.blue;
       } else {
           resId = R.color.red;
       }
       return resId;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeParcelable((Parcelable) this.store, 0);
        dest.writeString(this.amount);
        dest.writeString(String.valueOf(this.foodType));
        dest.writeFloat(this.price);
        dest.writeInt(this.imgSrc);
        dest.writeByte(isAvailable ? (byte) 1 : (byte) 0);
        dest.writeString(this.discountValue);
        dest.writeInt(this.likeCount);
    }

    public static final Parcelable.Creator<Food> CREATOR = new Parcelable.Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel source) {
            return new Food(source);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        Food other = (Food) o;
        if(!Objects.equals(this.store, other.store)) return false;
        if(!Objects.equals(this.name, other.name)) return false;
        if(!Objects.equals(this.amount, other.amount)) return false;
        if(!Objects.equals(this.foodType, other.foodType)) return false;
        if(!Objects.equals(this.discountValue, other.discountValue)) return false;
        if(this.price != other.price) return false;
        if(this.imgSrc != other.imgSrc) return false;
        if(this.isAvailable != other.isAvailable) return false;
        if(this.likeCount != other.likeCount) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (name != null ? name.hashCode() : 0);
        result = 31 * result + (store != null ? store.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (foodType != null ? foodType.hashCode() : 0);
        result = 31 * result + (int) price;
        result = 31 * result + imgSrc;
        result = 31 * result + (isAvailable ? 1 : 0);
        result = 31 * result + (discountValue != null ? discountValue.hashCode() : 0);
        result = 31 * result + likeCount;
        return result;
    }


}

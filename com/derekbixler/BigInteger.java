package com.derekbixler;

public class BigInteger {

    public static final int MAXBIGINTBYTES = 1048576;
    public int numDigits;
    public short digits_b256[];

    BigInteger() {
        this.numDigits = 0;
        this.digits_b256 = new short[MAXBIGINTBYTES];
        this.clear();
    }

    BigInteger(String hexString) {
        this.numDigits = 0;
        this.digits_b256 = new short[MAXBIGINTBYTES];
        this.setFromHexString(hexString);
    }

    BigInteger(int i) {
        this.numDigits = 0;
        this.digits_b256 = new short[MAXBIGINTBYTES];
        String hexString = Integer.toHexString(i);
        this.setFromHexString(hexString);
    }

    BigInteger(String hexString, String varName) {
        this.numDigits = 0;
        this.digits_b256 = new short[MAXBIGINTBYTES];
        this.setFromHexString(hexString);
        System.out.println(varName + " = " + this.hexString());
    }

    public BigInteger add(BigInteger y) {
        int maxDigit = this.numDigits > y.numDigits ? this.numDigits : y.numDigits;
        int carry = 0;

        BigInteger sum = new BigInteger();
        sum.numDigits = maxDigit;

        for (int digitIndex = 0; digitIndex < maxDigit; digitIndex++) {
            int tmp = this.digits_b256[digitIndex] + y.digits_b256[digitIndex] + carry;
            sum.digits_b256[digitIndex] = (short) (tmp % 256);
            carry = tmp / 256;
        }

        if (carry > 0) {
            sum.numDigits++;
            sum.digits_b256[maxDigit] = 1;
        }

        return sum;
    }

    public BigInteger multiply(BigInteger y) {
        int xDigit;
        int yDigit;
        BigInteger product = new BigInteger();

        for (xDigit = 0; xDigit < this.numDigits; xDigit++) {
            int carry = 0;
            BigInteger lineProduct = new BigInteger();

            for (yDigit = 0; yDigit < y.numDigits; yDigit++) {
                int digitProduct = this.digits_b256[xDigit] * y.digits_b256[yDigit] + carry;
                lineProduct.digits_b256[yDigit + xDigit] = (short) (digitProduct % 256);
                carry = digitProduct / 256;
            }

            if (carry != 0) {
                lineProduct.digits_b256[xDigit + yDigit] = (short) carry;
                lineProduct.numDigits = xDigit + yDigit + 1;
            } else {
                lineProduct.numDigits = xDigit + yDigit;
            }

            product = product.add(lineProduct);

        }

        return product;
    }

    void clear() {
        this.numDigits = 0;
        for (int i = 0; i < MAXBIGINTBYTES; i++) {
            this.digits_b256[i] = 0;
        }
    }

    int compare(BigInteger y) {
        if (this.numDigits < y.numDigits) {
            return -1;
        } else if (this.numDigits > y.numDigits) {
            return 1;
        } else {
            int lastIndex = this.numDigits - 1;
            for (int i = lastIndex; i >= 0; i--) {
                if (this.digits_b256[i] < y.digits_b256[i]) {
                    return -1;
                } else if (this.digits_b256[i] > y.digits_b256[i]) {
                    return 1;
                }
            }
            return 0;
        }
    }

    String hexString() {
        String ret;
        ret = "";
        for (int i = this.numDigits - 1; i >= 0; i--) {
            int partval = this.digits_b256[i];
            if (this.digits_b256[i] <= 15) {
                ret += "0" + Integer.toHexString(this.digits_b256[i]);
            } else {
                ret += Integer.toHexString(this.digits_b256[i]);
            }
        }
        return ret;
    }

    int numBits() {
        short leftmostDigit = this.digits_b256[this.numDigits - 1];
        return (int) (8 * (this.numDigits - 1) + Math.floor(Math.log(leftmostDigit)) + 1);
    }

    void setFromHexString(String s) {
        int strIndex = 0;
        int sLength = s.length();
        short tmpShort;
        String tmpStr;
        int digitIndex = this.numDigits - 1;

        if (s.length() % 2 == 1) {
            s = "0" + s;
        }
        for (; strIndex < sLength; strIndex += 2) {
            tmpStr = s.substring(strIndex, strIndex + 2);
            this.digits_b256[strIndex / 2] = Short.parseShort(tmpStr, 16);
            this.numDigits += 1;
            digitIndex--;
        }
    }

    void setFromLong(int y) {
        String yHex = Integer.toHexString(y);
        this.setFromHexString(yHex);
    }

    void print() {
        System.out.println("BigInteger: " + this.hexString());
    }
}

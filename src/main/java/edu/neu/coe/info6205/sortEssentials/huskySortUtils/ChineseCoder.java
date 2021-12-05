package edu.neu.coe.info6205.sortEssentials.huskySortUtils;

import java.text.Collator;

/**
 * ChineseCoder class is used while sorting chinese strings.
 * It uses a specified collator either In Built or com.ibm.icu Collator to do the husky encoding.
 */
public class ChineseCoder implements HuskyCoder<String>{

    private Collator collator;
    private com.ibm.icu.text.Collator ibmCollator;
    boolean inBuiltCollator;

    public ChineseCoder(Collator cl) {
        collator=cl;
        inBuiltCollator=true;
    }

    public ChineseCoder(com.ibm.icu.text.Collator cl) {
        ibmCollator=cl;
        inBuiltCollator=false;
    }

    @Override
    public long huskyEncode(String x) {
       String strRep= inBuiltCollator ?  new String(collator.getCollationKey(x).toByteArray()) : new String(ibmCollator.getCollationKey(x).toByteArray());
      return HuskyCoderFactory.unicodeCoder.huskyEncode(strRep);
    //   byte[] byteArray = inBuiltCollator ?  collator.getCollationKey(x).toByteArray() : ibmCollator.getCollationKey(x).toByteArray();
      //  return huskyEncode(byteArray);
    }

    @Override
    public boolean usingInBuiltCollator() {
        return inBuiltCollator;
    }

    @Override
    public boolean usingIBMCollator() {
        return !inBuiltCollator;
    }

    @Override
    public Collator getInBuiltCollator() {
        return collator;
    }

    @Override
    public com.ibm.icu.text.Collator getIBMCollator() {
         return ibmCollator;
    }

    public long huskyEncode(byte[] byteArray){
        long result = 0L;
        for (int i = 0; i < byteArray.length && i < 7; i++) result = (result << 8) | byteArray[i];
        return result;
    }
}

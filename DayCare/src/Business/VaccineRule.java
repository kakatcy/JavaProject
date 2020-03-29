/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

/**
 *
 * @author jxw
 */
public class VaccineRule {
    private String name;
    private int times;
    private int[] period;
    public VaccineRule(String info) {
       String[] field = info.split(",");
       name = field[0];
       times = Integer.parseInt(field[1]);
       period = new int[times];
       for(int i = 0; i<times; i++){
           period[i] = Integer.parseInt(field[2+i]);
       }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public int[] getPeriod() {
        return period;
    }

    public void setPeriod(int[] period) {
        this.period = period;
    }
    
}

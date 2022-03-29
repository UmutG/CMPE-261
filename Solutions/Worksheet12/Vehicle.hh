#ifndef _Vehicle_hh_
#define _Vehicle_hh_

class Vehicle {
  private:
    float speed;
    float mile;
  public:
    Vehicle();
    Vehicle(float, float);

    float getSpeed();
    float getMile();

    void setSpeed(float);
    void setMile(float);

    void print();
};

#endif

#include "Vehicle.hh"

class Ship: public Vehicle {
  private:
    int passenger;

  public:
    Ship();
    Ship(int, float, float);

    int getPassenger();
    void setPassenger(int);

    void print();
};

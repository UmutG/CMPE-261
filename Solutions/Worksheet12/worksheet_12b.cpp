// Umut Geyik - 120200145 - CMPE 261.01/0102

#include "Vehicle.hh"
#include "Ship.hh"
#include <iostream>
using namespace std;

// *** DEFINE --VEHICLE-- FUNCTIONS FOR PART 1 ***

// For vehicle class, we have 2 float variables, one default constructor
// and one overloaded constructor with 2 float variables. Let's define them.

Vehicle::Vehicle(){
	// Default speed and mile values for a vehicle object
	speed = 100;
	mile = 100;
}

Vehicle::Vehicle(float speed, float mile){
	// Overloaded constructor to determine a vehicle's speed and mile
	this -> speed = speed;
	this -> mile = mile;
}

float Vehicle::getSpeed(){
	// Return the speed of the vehicle
	return speed;
}

float Vehicle::getMile(){
	// Return the mile of the vehicle
	return mile;
}

void Vehicle::setSpeed(float speed){
	// Set a new speed for the vehicle
	this -> speed = speed;
}

void Vehicle::setMile(float mile){
	// Set a new mile for the vehicle
	this -> mile = mile;
}

// *** DEFINE --SHIP--    FUNCTIONS FOR PART 2 ***

// Since ship class inherited attributes from Vehicle class,
// we have to include them in the overloaded constructor.
Ship::Ship(){
	// Default values for a ship object
	passenger = 150;
}

Ship::Ship(int passenger, float speed, float mile){
	// Overloaded constructor to determine passenger number, speed and mile
	this -> passenger = passenger;
	setSpeed(speed);
	setMile(mile);
}

int Ship::getPassenger(){
	// Return passenger number of the ship
	return passenger;
}

void Ship::setPassenger(int passenger){
	// Set new passenger number for the ship
	this -> passenger = passenger;
}

void reduceSpeed(Ship*, float);
void takePassenger(Ship*, int);

int main() {

    Ship s(100, 200, 300);
    cout << s.getSpeed() << "\n";
    
    Vehicle v;
    cout << v.getMile() << "\n";
    
    reduceSpeed(&s, 0.2);
    takePassenger(&s, 12);
    takePassenger(&s, 89);
    
  return 0;
}

// reduce the speed of ship given in percentage (0 < percentage < 1).
void reduceSpeed(Ship *s, float percentage) {
  // *** FILL THIS FUNCTION FOR PART 3 ***
  
  // Here, we have to reduce the ship's speed by given percentage
  // To do that, we can basically multiply the speed of the ship with 
  // given percentage. Thus, its speed will be automatically reduced.
  float reduce = s -> getSpeed() * percentage;
  s -> setSpeed(s -> getSpeed() - reduce);
  cout << "New speed of the ship: " << s -> getSpeed() << " km/h\n";
}

// takes the number of passengers to the ship
void takePassenger(Ship *s, int pas) {
  // *** FILL THIS FUNCTION FOR PART 4 ***
  
  // Every ship has different capacity for passengers. So, we cannot let
  // exceeding number of passengers to aboard. Thus, we have to check
  // whether we can welcome them. Inform the passengers about the situation.
  int totalPas = s -> getPassenger();
  if (pas < totalPas){
  	int newPas = s -> getPassenger() - pas;
  	s -> setPassenger(newPas);
  	cout << "Welcome Aboard! Our journey will start soon." << "\n";
  	cout << "Current capacity is: " << s -> getPassenger() << "\n";
  }
  else{
  	cout << "We cannot take you aboard. Our capacity is: " << s -> getPassenger() << "\n";
  }
}

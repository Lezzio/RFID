#define RELAIS_FIRST 1
#define RELAIS_SECOND 2
#define RELAIS_THIRD 3
#define RELAIS_FOURTH 4

int openState = LOW;
int closeState = LOW;
int thirdState = LOW;

int ASK_ROLE = 3;
int ROLE = 1; //Controller

byte GATE_OPEN = 0;
byte GATE_CLOSE = 1;
byte GATE_ROTATE_ON = 4;
byte GATE_ROTATE_OFF = 5;

void setup() {
  //Setup hardware
  pinMode(RELAIS_FIRST, OUTPUT);
  pinMode(RELAIS_SECOND, OUTPUT);
  pinMode(RELAIS_THIRD, OUTPUT);
  pinMode(RELAIS_FOURTH, OUTPUT);

  close();
  
   //Command handling
  while(Serial.available() <= 0) {}
  
    int input = Serial.read();
    if(input == ASK_ROLE) {
     Serial.print(ROLE);
     Serial.flush();
   }
   delay(2500); //Waiting for other side to init
   
}
void loop() {
   if(Serial.available() > 0) {
    byte input = Serial.read();
    if(input == GATE_OPEN) {
      open();
    }
    if(input == GATE_CLOSE) {
      close();
    }
   }
}

void open() {
  digitalWrite(RELAIS_FIRST, HIGH);
  digitalWrite(RELAIS_THIRD, HIGH);
  
  digitalWrite(RELAIS_SECOND, LOW);
  digitalWrite(RELAIS_FOURTH, LOW);
}

void close() {
  digitalWrite(RELAIS_FIRST, LOW);
  digitalWrite(RELAIS_THIRD, LOW);
  
  digitalWrite(RELAIS_SECOND, HIGH);
  digitalWrite(RELAIS_FOURTH, HIGH);
}


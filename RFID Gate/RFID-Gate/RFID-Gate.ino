#define RELAIS_FIRST 2
#define RELAIS_SECOND 3
#define RELAIS_THIRD 4

int openState = LOW;
int closeState = LOW;
int thirdState = LOW;

void setup() {
  //Setup states
  pinMode(RELAIS_FIRST, OUTPUT);
  pinMode(RELAIS_SECOND, OUTPUT);
  pinMode(RELAIS_THIRD, OUTPUT);
  digitalWrite(RELAIS_FIRST, openState);
  digitalWrite(RELAIS_SECOND, closeState);
  digitalWrite(RELAIS_THIRD, thirdState);
}
void loop() {
}

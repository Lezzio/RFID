#include <Wire.h>
#include <SPI.h>
#include <Adafruit_PN532.h>

#define PN532_SCK  (2)
#define PN532_MOSI (3)
#define PN532_SS   (4)
#define PN532_MISO (5)

#define PN532_IRQ   (2)
#define PN532_RESET (3)  // Not connected by default on the NFC Shield

Adafruit_PN532 nfc(PN532_IRQ, PN532_RESET);

int ASK_ROLE = 3;
int ROLE = 0; //Reader

void setup(void) {
  
  Serial.begin(115200);
  nfc.begin();

  uint32_t versiondata = nfc.getFirmwareVersion();
  nfc.SAMConfig();
  nfc.selectAll();
  
   //Command handling
  while(Serial.available() <= 0) {}
  
    int input = Serial.read();
    if(input == ASK_ROLE) {
     Serial.print(ROLE);
     Serial.flush();
   }
   delay(2500); //Waiting for other side to init
  
}

void loop(void) {
  uint8_t success;
  uint8_t uid[] = { 0, 0, 0, 0, 0, 0, 0 };  // Buffer to store the returned UID
  uint8_t uidLength;                        // Length of the UID (4 or 7 bytes depending on ISO14443A card type)
  
  success = nfc.readPassiveTargetID(PN532_MIFARE_ISO14443A, uid, &uidLength); //Blocking
  
  if (success) {
    nfc.PrintHex(uid, uidLength);
    Serial.print("#");
    Serial.flush();
  }
  
}



#define encoderIntegralSize 10

typedef union __attribute__((__packed__)) _AS5055CommandPacket
		{
			UINT16	uint0_15;
                        struct
			{
                            uint8_t   ubyte0_7;
                            uint8_t   ubyte8_15;
                        } bytes;
			struct
			{
				unsigned PAR:1;
				unsigned Address:14;
				unsigned RWn:1;
			} regs;
		} AS5055CommandPacket;

typedef union __attribute__((__packed__)) _AS5055ReadPacket
		{
			UINT16	uint0_15;
                        struct
			{
                            uint8_t   ubyte0_7;
                            uint8_t   ubyte8_15;
                        } bytes;
                        struct
			{
                            unsigned zero:1;
                            unsigned one:1;
                            unsigned two:1;
                            unsigned three:1;
                            unsigned four:1;
                            unsigned five:1;
                            unsigned six:1;
                            unsigned seven:1;
                            unsigned eight:1;
                            unsigned nine:1;
                            unsigned ten:1;
                            unsigned elevin:1;
                            unsigned twelve:1;
                            unsigned thirteen:1;
                            unsigned fourteen:1;
                            unsigned fifteen:1;
                        } bits;
			struct
			{
				unsigned PAR:1;
                                unsigned EF:1;
				unsigned Data:14;
			} regs;
		} AS5055ReadPacket;

typedef union __attribute__((__packed__)) _AS5055AngularDataPacket
		{
			UINT16	uint0_15;
                        struct
			{
                            uint8_t   ubyte0_7;
                            uint8_t   ubyte8_15;
                        } bytes;
                        struct
			{
                            unsigned zero:1;
                            unsigned one:1;
                            unsigned two:1;
                            unsigned three:1;
                            unsigned four:1;
                            unsigned five:1;
                            unsigned six:1;
                            unsigned seven:1;
                            unsigned eight:1;
                            unsigned nine:1;
                            unsigned ten:1;
                            unsigned elevin:1;
                            unsigned twelve:1;
                            unsigned thirteen:1;
                            unsigned fourteen:1;
                            unsigned fifteen:1;
                        } bits;
			struct
			{
				unsigned PAR:1;
                                unsigned EF:1;
				unsigned Data:12;
                                unsigned AlarmLO:1;
                                unsigned AlarmHI:1;
			} regs;
		} AS5055AngularDataPacket;
typedef union __attribute__((__packed__)) _AS5055SystemConfigPacket
		{
			UINT16	uint0_15;
                        struct
			{
                            uint8_t   ubyte0_7;
                            uint8_t   ubyte8_15;
                        } bytes;
                                                struct
			{
                            unsigned zero:1;
                            unsigned one:1;
                            unsigned two:1;
                            unsigned three:1;
                            unsigned four:1;
                            unsigned five:1;
                            unsigned six:1;
                            unsigned seven:1;
                            unsigned eight:1;
                            unsigned nine:1;
                            unsigned ten:1;
                            unsigned elevin:1;
                            unsigned twelve:1;
                            unsigned thirteen:1;
                            unsigned fourteen:1;
                            unsigned fifteen:1;
                        } bits;
			struct
			{
				unsigned PAR:1;
                                unsigned EF:1;
                                unsigned unused:2;
                                unsigned break_loop:1;
                                unsigned gain:2;
                                unsigned bw:2;
				unsigned invert:1;
                                unsigned id:3;
                                unsigned resolution:2;
			} regs;
		} AS5055SystemConfigPacket;

typedef union __attribute__((__packed__)) _AS5055WritePacket
		{
			UINT16	uint0_15;
                        struct
			{
                            uint8_t   ubyte0_7;
                            uint8_t   ubyte8_15;
                        } bytes;
                                                struct
			{
                            unsigned zero:1;
                            unsigned one:1;
                            unsigned two:1;
                            unsigned three:1;
                            unsigned four:1;
                            unsigned five:1;
                            unsigned six:1;
                            unsigned seven:1;
                            unsigned eight:1;
                            unsigned nine:1;
                            unsigned ten:1;
                            unsigned elevin:1;
                            unsigned twelve:1;
                            unsigned thirteen:1;
                            unsigned fourteen:1;
                            unsigned fifteen:1;
                        } bits;
			struct
			{
				unsigned PAR:1;
                                unsigned unassigned1:1;
				unsigned Data:14;
			} regs;
		} AS5055WritePacket;

// See 7.2.4 in AS5055 Datasheet
        #define     AS5055_READ                         1
        #define     AS5055_WRITE                        0

        #define     AS5055REG_MasterReset               0x33A5
        #define     AS5055REG_PowerOnReset              0x3f22
        #define     AS5055REG_SoftwareReset             0x3c00
        #define     AS5055REG_ClearErrorFlagReset       0x3380
        #define     AS5055REG_NoOperation               0x0000
        #define     AS5055REG_AutomaticGainControl      0x3ff8
        #define     AS5055REG_AngularData               0x3fff
        #define     AS5055REG_SystemConfig1             0x3f20
//Perfoems a batch encoder read of all channels
void updateAllEncoders();

//Returns the latest encoder reading
float getRecentEncoderReading(int index);

float readEncoder(uint8_t index);

uint8_t   AS5055CalculateParity(uint16_t data);

uint16_t AS5055readAngle(uint8_t index);

uint16_t AS5055reset(uint8_t index);

void initializeEncoders();
void disableWrapping();
void EncoderSS(uint8_t index, uint8_t state);
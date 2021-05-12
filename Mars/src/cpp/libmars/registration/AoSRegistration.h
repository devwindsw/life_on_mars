#ifndef _AOS_REGISTRATION_H_
#define _AOS_REGISTRATION_H_

#include <IAoSAppContext.h>

class AoSRegistration
{
public:
    AoSRegistration(IAoSAppContext *_piAppContext);
    virtual ~AoSRegistration();

    virtual unsigned int GetState();

protected:
    void SetState(unsigned int nState);

    unsigned int nState;
    IAoSAppContext *piContext;
};
#endif // _AOS_REGISTRATION_H_

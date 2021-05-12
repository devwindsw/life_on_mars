#ifndef _I_AOS_APP_CONTEXT_H_
#define _I_AOS_APP_CONTEXT_H_

class IAoSAppContext
{
public:
    virtual ~IAoSAppContext() {}
    virtual int GetSlotId() = 0;
};
#endif // _I_AOS_APP_CONTEXT_H_

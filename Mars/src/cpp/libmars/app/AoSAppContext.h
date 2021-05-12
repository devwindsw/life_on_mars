#ifndef _AOS_APP_CONTEXT_H_
#define _AOS_APP_CONTEXT_H_

#include <IAoSAppContext.h>

class AoSAppContext
    : public IAoSAppContext
{
public:
    AoSAppContext();
    virtual ~AoSAppContext();

    virtual int GetSlotId();

private:
    void SetSlotId(int nSlotId);

    int nSlotId;
};
#endif // _AOS_APP_CONTEXT_H_

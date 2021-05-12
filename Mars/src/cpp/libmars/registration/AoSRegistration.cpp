#include "AoSRegistration.h"

AoSRegistration::AoSRegistration(IAoSAppContext *_piAppContext)
    : piContext(_piAppContext)
{
}

AoSRegistration::~AoSRegistration()
{
}

unsigned int AoSRegistration::GetState()
{
    return nState;
}

void AoSRegistration::SetState(unsigned int nState)
{
  this->nState = nState;
}

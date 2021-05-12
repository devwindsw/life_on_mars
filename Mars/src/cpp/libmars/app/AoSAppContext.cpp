#include "AoSAppContext.h"

AoSAppContext::AoSAppContext()
    : nSlotId(0)
{
}

AoSAppContext::~AoSAppContext()
{
}

void AoSAppContext::SetSlotId(int nSlotId)
{
    this->nSlotId = nSlotId;
}

int AoSAppContext::GetSlotId()
{
  return nSlotId;
}

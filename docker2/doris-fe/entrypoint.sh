#!/bin/bash

echo "fe_role:"$DORIS_ROLE
echo "leader:"$DORIS_LEADER

if [[ $DORIS_ROLE = 'fe-leader' ]]; then
    /home/doris/fe/bin/start_fe.sh
elif [[ $DORIS_ROLE = 'be' ]]; then
    /home/doris/be/bin/start_be.sh
elif [[ $DORIS_ROLE = 'fe-follower' ]]; then
    /home/doris/fe/bin/start_fe.sh --helper $DORIS_LEADER
else
    /home/doris/fs_broker/bin/start_broker.sh
fi
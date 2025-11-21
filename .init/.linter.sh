#!/bin/bash
cd /home/kavia/workspace/code-generation/quickride-booking-platform-45234-45243/taxi_booking_backend
./gradlew checkstyleMain
LINT_EXIT_CODE=$?
if [ $LINT_EXIT_CODE -ne 0 ]; then
   exit 1
fi


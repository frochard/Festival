#!/usr/bin/env bash

curl -X DELETE localhost:5000/users/$1 -H 'Accept: application/json' 

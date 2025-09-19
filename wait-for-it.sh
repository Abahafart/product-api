#!/usr/bin/env bash
# wait-for-it.sh: Wait until a host and port are available

set -e

host="$1"
port="$2"
shift 2

for i in {1..60}; do
  nc -z "$host" "$port" && echo "Host $host port $port is up!" && exec "$@"
  echo "Waiting for $host:$port... ($i/60)"
  sleep 1
done

echo "Timeout waiting for $host:$port" >&2
exit 1

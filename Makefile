.PHONY: init env

init:
	bash -c "./scripts/init.sh"

env:
	bash -c "./scripts/env.sh $(filter-out env,$(MAKECMDGOALS))"

%:
	@:

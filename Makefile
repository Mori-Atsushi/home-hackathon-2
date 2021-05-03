.DEFAULT_GOAL := help
SHELL=/bin/bash

.PHONY: help
help:
	@echo "Usage: "
	@echo "	make init		...	init proto files all"
	@echo "	make update		...	update proto generated files"
	@echo "	make update-go		...	update .go files only"
	@echo "	make clean-go		...	delete .go files which related in proto generated"
	@echo ""

.PHONY: update-go update-swift
init: update-go update-swift

.PHONY: init
update: init

.PHONY: clean-go
update-go: clean-go
	protoc --proto_path=proto --go_out=./backend/app/gen --go_opt=paths=source_relative \
  --go-grpc_out=./backend/app/gen --go-grpc_opt=paths=source_relative \
  proto/app.proto

clean-go:
	rm -rf ./backend/app/gen/*.go


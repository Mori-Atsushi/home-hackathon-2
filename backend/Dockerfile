FROM golang:1.16.3-alpine
RUN apk update && apk add git
RUN mkdir /go/src/app
WORKDIR /go/src/app
COPY ./app/go.mod .
COPY ./app/gen/go.mod ./gen/go.mod
COPY ./app/go.sum .
RUN go mod download
COPY ./app/main.go .
COPY ./app/gen/ ./gen/
RUN go build -o /go/src/app/application
FROM golang:1.16.3-alpine
RUN apk update && apk add git
RUN mkdir /go/src/app
WORKDIR /go/src/app
COPY ./app/go.mod .
RUN go mod download
COPY ./app/main.go .
RUN go build -o /go/src/app/application
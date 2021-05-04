FROM golang:1.16.3-alpine
RUN apk update && apk add git mysql-client
RUN mkdir /go/src/app
WORKDIR /go/src/app

ENV DOCKERIZE_VERSION v0.6.1
RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && rm dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz

COPY ./app/ .
RUN go mod download
COPY ./app/main.go .
COPY ./app/gen/ ./gen/
RUN go build -o /go/src/app/application

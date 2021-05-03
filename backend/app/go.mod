module com.home-hackathon-2/backend

go 1.16

require (
	github.com/go-sql-driver/mysql v1.6.0
	github.com/google/uuid v1.1.2
	github.com/jmoiron/sqlx v1.3.3
	google.golang.org/grpc v1.35.0
	local.packages/gen v0.0.0-00010101000000-000000000000
)

replace local.packages/gen => ./gen

package domain

type Auth struct {
	UserID      string
	AccessToken string
}

func NewAuth(userID string, accessToken string) Auth {
	return Auth{
		UserID:      userID,
		AccessToken: accessToken,
	}
}

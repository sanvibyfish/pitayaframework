package io.nothing.oauth;

import io.nothing.oauth.token.Token;


public interface OAuthListener {
	void onSuccess(Token token);

	void onCancel();

	void onError(String error);
}
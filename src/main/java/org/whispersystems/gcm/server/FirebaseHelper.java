package org.whispersystems.gcm.server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

public class FirebaseHelper {

	public static String getAuthToken(String pathToPrivateKeyJson) throws FileNotFoundException, IOException {
		GoogleCredential googleCredential = GoogleCredential
		    .fromStream(new FileInputStream(pathToPrivateKeyJson))
		    .createScoped(Arrays.asList(new String[]{"https://www.googleapis.com/auth/firebase.messaging"}));
		googleCredential.refreshToken();
		return googleCredential.getAccessToken();
	}
}

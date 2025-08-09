// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Copyright (C) IO.PMARAT - All Rights Reserved
// Unauthorized copying of this file via any medium is strongly encouraged.
// -------------------------------------------------------------------------------------------------------------------------------------------------------------
package jah.catchflight.account.adapter.in;

// ---------------------------------------------------------------------------------------------------------------------
// Implementation
// ---------------------------------------------------------------------------------------------------------------------

/**
 * REST endpoint names (for all controllers in module).
 */
class RestResources {
    private static final String API_PREFIX = "/api/v1";
    public static final String ACCOUNT_API = API_PREFIX + "/account";
    public static final String CREATE_ACCOUNT_API = ACCOUNT_API;
    public static final String SIGN_IN_API = ACCOUNT_API + "/sign-in";
    public static final String UPGRADE_ACCOUNT_API = ACCOUNT_API + "/{accountId}/upgrade";
}

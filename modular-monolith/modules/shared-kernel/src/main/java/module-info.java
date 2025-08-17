/**
 * The sharedkernel module provides core abstractions and utilities for the CatchFlight application.
 * It defines common interfaces, data models, and query structures used across multiple modules.
 * This module depends on the 'common' module for foundational utilities and exposes the account
 * and query packages for use by other modules.
 */
module sharedkernel {
    requires common;
    exports jah.catchflight.sharedkernel.account;
    exports jah.catchflight.sharedkernel.query;
}

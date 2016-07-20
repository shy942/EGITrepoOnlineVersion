/***/
package org.eclipse.jface.resource;

/**
* Thrown when allocation of an SWT device resource fails
*
* @since 3.1
*/
public class DeviceResourceException extends RuntimeException {

    private Throwable cause;

    /**
* All serializable objects should have a stable serialVersionUID
*/
    private static final long serialVersionUID = 11454598756198L;

    /**
* Creates a DeviceResourceException indicating an error attempting to
* create a resource and an embedded low-level exception describing the cause
*
* @param missingResource
* @param cause cause of the exception (or null if none)
*/
    public  DeviceResourceException(DeviceResourceDescriptor missingResource, Throwable cause) {
        //$NON-NLS-1$
        super("Unable to create resource " + missingResource.toString());
        // don't pass the cause to super, to allow compilation against JCL Foundation (bug 80059)
        this.cause = cause;
    }

    /**
* Creates a DeviceResourceException indicating an error attempting to
* create a resource
*
* @param missingResource
*/
    public  DeviceResourceException(DeviceResourceDescriptor missingResource) {
        this(missingResource, null);
    }

    /**
* Returns the cause of this throwable or <code>null</code> if the
* cause is nonexistent or unknown.
*
* @return the cause or <code>null</code>
* @since 3.1
*/
    @Override
    public Throwable getCause() {
        return cause;
    }
}

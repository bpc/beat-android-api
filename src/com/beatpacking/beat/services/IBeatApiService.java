package com.beatpacking.beat.services;/*___Generated_by_IDEA___*/

/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Users/Eugene/beat/beat-android/app/src/main/java/com/beatpacking/beat/services/IBeatApiService.aidl
 */

/**
 * Created by Eugene on 7/25/14.
 */
public interface IBeatApiService extends android.os.IInterface
{
    /** Local-side IPC implementation stub class. */
    public static abstract class Stub extends android.os.Binder implements com.beatpacking.beat.services.IBeatApiService
    {
        private static final String DESCRIPTOR = "com.beatpacking.beat.services.IBeatApiService";
        /** Construct the stub at attach it to the interface. */
        public Stub()
        {
            this.attachInterface(this, DESCRIPTOR);
        }
        /**
         * Cast an IBinder object into an com.beatpacking.beat.services.IBeatApiService interface,
         * generating a proxy if needed.
         */
        public static com.beatpacking.beat.services.IBeatApiService asInterface(android.os.IBinder obj)
        {
            if ((obj==null)) {
                return null;
            }
            android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (((iin!=null)&&(iin instanceof com.beatpacking.beat.services.IBeatApiService))) {
                return ((com.beatpacking.beat.services.IBeatApiService)iin);
            }
            return new com.beatpacking.beat.services.IBeatApiService.Stub.Proxy(obj);
        }
        @Override public android.os.IBinder asBinder()
        {
            return this;
        }
        @Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
        {
            switch (code)
            {
                case INTERFACE_TRANSACTION:
                {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                case TRANSACTION_setHeadVisible:
                {
                    data.enforceInterface(DESCRIPTOR);
                    boolean _arg0;
                    _arg0 = (0!=data.readInt());
                    this.setHeadVisible(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_isHeadVisible:
                {
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result = this.isHeadVisible();
                    reply.writeNoException();
                    reply.writeInt(((_result)?(1):(0)));
                    return true;
                }
                case TRANSACTION_setHeadLocation:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    int _arg1;
                    _arg1 = data.readInt();
                    this.setHeadLocation(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_getHeadX:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _result = this.getHeadX();
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                }
                case TRANSACTION_getHeadY:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _result = this.getHeadY();
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                }
                case TRANSACTION_pause:
                {
                    data.enforceInterface(DESCRIPTOR);
                    this.pause();
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_resume:
                {
                    data.enforceInterface(DESCRIPTOR);
                    this.resume();
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_isAuthenticated:
                {
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result = this.isAuthenticated();
                    reply.writeNoException();
                    reply.writeInt(((_result)?(1):(0)));
                    return true;
                }
            }
            return super.onTransact(code, data, reply, flags);
        }
        private static class Proxy implements com.beatpacking.beat.services.IBeatApiService
        {
            private android.os.IBinder mRemote;
            Proxy(android.os.IBinder remote)
            {
                mRemote = remote;
            }
            @Override public android.os.IBinder asBinder()
            {
                return mRemote;
            }
            public String getInterfaceDescriptor()
            {
                return DESCRIPTOR;
            }
            @Override public void setHeadVisible(boolean b) throws android.os.RemoteException
            {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(((b)?(1):(0)));
                    mRemote.transact(Stub.TRANSACTION_setHeadVisible, _data, _reply, 0);
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            @Override public boolean isHeadVisible() throws android.os.RemoteException
            {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                boolean _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    mRemote.transact(Stub.TRANSACTION_isHeadVisible, _data, _reply, 0);
                    _reply.readException();
                    _result = (0!=_reply.readInt());
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }
            @Override public void setHeadLocation(int x, int y) throws android.os.RemoteException
            {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(x);
                    _data.writeInt(y);
                    mRemote.transact(Stub.TRANSACTION_setHeadLocation, _data, _reply, 0);
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            @Override public int getHeadX() throws android.os.RemoteException
            {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                int _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    mRemote.transact(Stub.TRANSACTION_getHeadX, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.readInt();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }
            @Override public int getHeadY() throws android.os.RemoteException
            {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                int _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    mRemote.transact(Stub.TRANSACTION_getHeadY, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.readInt();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }
            @Override public void pause() throws android.os.RemoteException
            {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    mRemote.transact(Stub.TRANSACTION_pause, _data, _reply, 0);
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            @Override public void resume() throws android.os.RemoteException
            {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    mRemote.transact(Stub.TRANSACTION_resume, _data, _reply, 0);
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            @Override public boolean isAuthenticated() throws android.os.RemoteException
            {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                boolean _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    mRemote.transact(Stub.TRANSACTION_isAuthenticated, _data, _reply, 0);
                    _reply.readException();
                    _result = (0!=_reply.readInt());
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }
        }
        static final int TRANSACTION_setHeadVisible = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
        static final int TRANSACTION_isHeadVisible = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
        static final int TRANSACTION_setHeadLocation = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
        static final int TRANSACTION_getHeadX = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
        static final int TRANSACTION_getHeadY = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
        static final int TRANSACTION_pause = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
        static final int TRANSACTION_resume = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
        static final int TRANSACTION_isAuthenticated = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
    }
    public void setHeadVisible(boolean b) throws android.os.RemoteException;
    public boolean isHeadVisible() throws android.os.RemoteException;
    public void setHeadLocation(int x, int y) throws android.os.RemoteException;
    public int getHeadX() throws android.os.RemoteException;
    public int getHeadY() throws android.os.RemoteException;
    public void pause() throws android.os.RemoteException;
    public void resume() throws android.os.RemoteException;
    public boolean isAuthenticated() throws android.os.RemoteException;
}

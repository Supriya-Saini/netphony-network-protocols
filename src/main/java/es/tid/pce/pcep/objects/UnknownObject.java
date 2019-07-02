package es.tid.pce.pcep.objects;

public class UnknownObject extends PCEPObject{

	private int adminGroup;
	
	public UnknownObject() {
		this.setObjectClass(ObjectParameters.PCEP_OBJECT_CLASS_UNKNOWN);
		this.setOT(ObjectParameters.PCEP_OBJECT_TYPE_UNKNOWN);
	}

	public UnknownObject(byte[] bytes, int offset)throws MalformedPCEPObjectException{	
		super(bytes,offset);
		decode();
	}

	public void encode(){
		ObjectLength=8;/* 4 bytes for header and 4 bytes for color */
		object_bytes=new byte[ObjectLength];
		encode_header();
		this.object_bytes[4]=(byte)(adminGroup >>> 24);
		this.object_bytes[5]=(byte)(adminGroup >> 16 & 0xff);
		this.object_bytes[6]=(byte)(adminGroup >> 8 & 0xff);
		this.object_bytes[7]=(byte)(adminGroup & 0xff);	
	}

	@Override
	public void decode()throws MalformedPCEPObjectException{
		if (ObjectLength!=8){
			throw new MalformedPCEPObjectException();
		}	
		int color=0;
		for (int k = 0; k < 4; k++) {
			color = (color << 8) | (object_bytes[k+8] & 0xff);
		}
		adminGroup=color;
		
	}

	public int getAdminGroup() {
		return adminGroup;
	}

	public void setAdminGroup(int adminGroup) {
		this.adminGroup = adminGroup;
	}
	public String toString()
	{
		return "AdminGroup : "+adminGroup;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + adminGroup;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UnknownObject other = (UnknownObject) obj;
		if (adminGroup != other.adminGroup)
			return false;
		return true;
	}
}

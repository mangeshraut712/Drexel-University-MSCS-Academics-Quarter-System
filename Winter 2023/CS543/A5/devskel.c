#include "u.h"
#include "../port/lib.h"
#include "mem.h"
#include "dat.h"
#include "kernel.h"
#include "fns.h"
#include "../port/error.h"

enum {
    Qdir,
    Qdata,
    Qctl,
};

Dirtab skeltab[] =
{
    ".", {Qdir, 0, QTDIR}, 0, 0555,
    "skeldata", {Qdata}, 0, 0666,
    "skelctl", {Qctl}, 0, 0666,
};

enum {
  Qbind
};

Cmdtab progcmd[] = {
    Qbind, "bind", 3,
};

Cmdbuf *cb;
Cmdtab *ct;
char *disk1, *disk2;
int desc1, desc2;

static Chan*
skelattach(char *spec)
{
    return devattach('K', spec);
}

static Walkqid*
skelwalk(Chan *c, Chan *nc, char **name, int nname)
{
    return devwalk(c, nc, name, nname, skeltab, nelem(skeltab), devgen);
}

static int
skelstat(Chan *c, uchar *db, int n)
{
    return devstat(c, db, n, skeltab, nelem(skeltab), devgen);
}

static Chan*
skelopen(Chan *c, int omode)
{
    return devopen(c, omode, skeltab, nelem(skeltab), devgen);
}

static void
skelclose(Chan *c)
{
    USED(c);
}

static long
skelread(Chan *c, void *va, long count, vlong offset)
{
	int nread1, nread2;
    	USED(offset);

    	if (c->qid.type & QTDIR) {
        	return devdirread(c, va, count, skeltab, nelem(skeltab), devgen);
   	}

	switch(c->qid.path) {
		case Qctl:
			return readstr(offset, va, count, "Skelctl");
		case Qdata:
	    		desc1 = kopen(disk1, OREAD);
                        nread1 = kpread(desc1, va, count, offset);
	    		if(nread1 < 0){
				desc2 = kopen(disk2, OREAD);
                                nread2 = kpread(desc2, va, count, offset);
				if (nread2<0){
					error("Error in reading from RAID");
				}
				else{
					kclose(desc1);
					kclose(desc2);
					return nread2;
				}
			}
			else{
				kclose(desc1);
				return nread1;
			}
	}
    	return 0;
}


static long
skelwrite(Chan *c, void *va, long count, vlong offset)
{
	int write1, write2;
	long disksize1, disksize2;
    	USED(offset);

	switch(c->qid.path) {
        	case Qctl:
            		cb = parsecmd(va, count);
            		if(waserror()){
                		free(cb);
                		nexterror();
            		}
            		ct = lookupcmd(cb, progcmd, nelem(progcmd));
            		switch(ct->index) {
                	case Qbind:
				disk1 = strdup(cb->f[1]);
				disk2 = strdup(cb->f[2]);
				break;
    			}
    			break;
        		case Qdata:
	    			desc1 = kopen(disk1, ORDWR);
	    			desc2 = kopen(disk2, ORDWR);
				if (desc1<0 || desc2<0)
					error("RAID1 bound failed");
				disksize1 = kseek(desc1,0,2);
				kseek(desc1,0,0);
				disksize2 = kseek(desc2,0,2);
				kseek(desc2,0,0);
	    			if(disksize1 != disksize2)
					error("Files are not of same size");
				skeltab[Qdata].length = disksize1;
				write1 = kpwrite(desc1, va, count, offset);
				if(write1 < 0){
                                	error("Error in writing from RAID");
				}
	    			write2 = kpwrite(desc2, va, count, offset);
				if(write2 < 0){
                                        error("Error in wring from RAID");
				}
    	    			if (write1 != write2) {
        				error("RAID 1 write failed: write values not equal");
    	    			}
				kclose(desc1);
				kclose(desc2);
				return write1;
            			break;
	}
	free(cb);
    	poperror();
	return count;
}


Dev skeldevtab = {
    'K',
    "skel",
    devreset,
    devinit,
    devshutdown,
    skelattach,
    skelwalk,
    skelstat,
    skelopen,
    devcreate,
    skelclose,
    skelread,
    devbread,
    skelwrite,
    devbwrite,
    devremove,
    devwstat,
};


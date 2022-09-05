package io.github.khanhdpdx01.veserver.util;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;

import java.io.IOException;

public class IpfsUtil {
    public static Multihash addContent(IPFS ipfs, byte[] data) {
        Multihash CID;
        try {
            NamedStreamable.ByteArrayWrapper is = new NamedStreamable.ByteArrayWrapper(data);
            MerkleNode response = ipfs.add(is).get(0);
            ipfs.refs.local();
            CID = response.hash;
        } catch (IOException ex) {
            throw new RuntimeException("Error whilst communicating with the IPFS node", ex);
        }
        return CID;
    }
}

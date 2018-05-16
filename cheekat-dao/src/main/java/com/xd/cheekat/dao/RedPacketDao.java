package com.xd.cheekat.dao;

import com.xd.cheekat.pojo.RedPacket;

public interface RedPacketDao {
    int deleteByPrimaryKey(Long redpacketId);

    int insert(RedPacket record);

    int insertSelective(RedPacket record);

    RedPacket selectByPrimaryKey(Long redpacketId);

    int updateByPrimaryKeySelective(RedPacket record);

    int updateByPrimaryKey(RedPacket record);

	RedPacket getRedPacketByRecordSN(String record_sn);

	boolean updateRedPacketByRecordSn(RedPacket redPacket);
}
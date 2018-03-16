package ThoughtWorks;

import ThoughtWorks.entry.Drone;
import ThoughtWorks.entry.Signal;
import ThoughtWorks.tools.Checking;

import java.util.*;

/**存放所有无人机记录和消息记录，并作出消息更新的逻辑判断*/
public class WatchCenter {
    HashMap<String, Drone> drones = new HashMap<>();
    List<Signal> signals = new ArrayList<>();

    /**读文件
     * @param scanner 一个文件字段扫描器*/
    public void readRecord(Scanner scanner) {
        int[] coors = new int[6];
        int count = 0; //count coordinary number

        while (scanner.hasNextLine()) {
            String droneId = scanner.next();
            if (!Checking.isValidId(droneId)) { //TODO 从id起就错了如何记录错误？
                scanner.nextLine();
                continue;
            }

            Signal newRecord = new Signal(droneId);
            Arrays.fill(coors, 0);
            count = 0;
            while (scanner.hasNextInt())
                coors[count++] = scanner.nextInt();

            if (count != 3 && count != 6) {
                newRecord.setError(true);
            } else if (count == 3) {
                init(newRecord, coors, droneId);
            }else { //6
                offset(newRecord, coors, droneId);
            }
            signals.add(newRecord);
        }//while end
    }

    /**初始化，针对未出现过的无人机id
     * @param newRecord 一条信息记录
     * @param coors 坐标数据
     * @param droneId 无人机ID*/
    private void init (Signal newRecord, int[] coors, String droneId) {
        if (drones.containsKey(droneId)) {
            newRecord.setError(true);
        } else {
            drones.put(droneId, new Drone(
                    droneId, coors[0], coors[1], coors[2]));
            newRecord.setOrigin(coors[0], coors[1], coors[2]);
            newRecord.setOffset(0, 0, 0);
        }
    }

    /**更新，针对已出现过的无人机id
     * @param newRecord 一条信息记录
     * @param coors 坐标数据
     * @param droneId 无人机ID*/
    private void offset(Signal newRecord, int[] coors, String droneId) {
        if (drones.containsKey(droneId)) {
            if (drones.get(droneId)
                    .equalsLocation(coors[0], coors[1], coors[2])) {
                drones.get(droneId).setLocation(
                        coors[0] + coors[3],
                        coors[1] + coors[4],
                        coors[2] + coors[5]);
                newRecord.setOrigin(coors[0], coors[1], coors[2]);
                newRecord.setOffset(coors[3], coors[4], coors[5]);
            } else {
                newRecord.setError(true);
            }
        } else { //6 num but no record before
            //drones.put(droneId, new Drone(droneId));
            newRecord.setError(true);
        }
    }

    /**获取指定消息ID的无人机处在的状态
     * @param signalId 消息ID*/
    public String getInfo(int signalId) {
        if (signalId >= signals.size())
            return "Cannot find " + signalId;

        Signal signal = signals.get(signalId);
        StringBuilder sb = new StringBuilder();
        if (signal.isError()) {
            sb.append("Error: ");
        } else {
            sb.append(signal.getDroneId());
            sb.append(' ');
        }

        sb.append(signalId);
        if (!signal.isError()) {
            sb.append(' ');
            sb.append(signal.getOriginX() + signal.getOffsetX());
            sb.append(' ');
            sb.append(signal.getOriginY() + signal.getOffsetY());
            sb.append(' ');
            sb.append(signal.getOriginZ() + signal.getOffsetZ());
        }

        return sb.toString();
    }
}

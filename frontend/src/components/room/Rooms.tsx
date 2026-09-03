import { roomApi } from "../../api/room"

async function Rooms() {
    const val = await roomApi.getRooms();

    return (
        <p>{val}</p>
    )
}

export default Rooms;

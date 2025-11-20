const std = @import("std");

pub fn main() !void {
    var file = try std.fs.cwd().openFile("rounds.txt", .{});
    defer file.close();
    var buffer: [1024]u8 = undefined;
    const n = try file.read(buffer[0..buffer.len]);
    var rounds = try std.fmt.parseInt(i64, std.mem.trim(u8, buffer[0..n], "\n"), 10);
    rounds += 2;
    var i: i64 = 2;
    var x: f64 = 1.0;
    var pi: f64 = 1.0;
    while (i < rounds) : (i += 1) {
        x = -x;
        pi += (x / @as(f64, @floatFromInt(2 * i - 1)));
    }
    pi *= 4;

    var stdout_buffer: [256]u8 = undefined;
    var stdout_writer = std.fs.File.stdout().writer(&stdout_buffer);
    try stdout_writer.interface.print("{d}", .{pi});
    try stdout_writer.interface.flush();
}
